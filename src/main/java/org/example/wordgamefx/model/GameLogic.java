/**
 * GameLogic.java
 *
 * Contains all the rules and the mechanics of the game:
 *      - Stores the hidden word (answer)
 *      - Keeps track of valid dictionary words
 *      - Checks whether a guess is valid
 *      - Compares a guess against the answer and provides feedback
 *      - Tracks current game state
 *
 */



package org.example.wordgamefx.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.*;

public class GameLogic
{

    List<String> answerWords; //list of potential answer words, to be chosen randomly
    Random random; //used to select a random word from the list of answer words
    String answerWord;

    //array to keep track of each character in the answer string
    private Character[] answerWordArray;

    //array to keep track of each character in the user-inputted guess string
    private Character[] guessWordArray;

    //arrays to keep track of which characters and character placements answerWordArray has in common with guessWordArray
    //lists to store the letters from guessWordArray in the appropriate category (gray, yellow, or green)
    //gray is true when a character in guessWordArray does not exist in answerWordArray
    private boolean[] gray;
    private List<Character> grayLetters;
    /* yellow is true when a character in guessWordArray exists in answerWordArray, but at a different index

       Additional condition: the combined number of yellow and green instances of a letter must not exceed
       the number of times that character appears in answerWordArray. For example:
       answerWord = candy
       guessWord == manta
       The first "a" in manta should be green. The second "a" in manta should be grey, NOT yellow.
    */
    private boolean[] yellow;
    private List<Character> yellowLetters;
    //green is true when a character in guessWordArray exists in answerWordArray, and is at the same index
    private boolean[] green;
    private List<Character> greenLetters;

    //keep track of guesses, 6 guesses = lose
    private int numGuesses;

    public GameLogic(){
        /*
        If you add a word to this list, verify that your word is included in the dictionary validation text file
        in the /src/main/resources folder.
        If it is not included, please add it to the text file in addition to this list.
        */
        answerWords = new ArrayList<>(Arrays.asList("candy", "witch", "ghoul", "mummy", "demon", "spook",
                "ghost", "eerie", "haunt", "crypt", "scare", "scary", "bones", "skull", "fangs", "blood",
                "treat", "trick", "grave", "carve", "broom", "devil", "beast", "coven", "curse", "night",
                "masks", "slime"));
        random = new Random();
        answerWord = answerWords.get(random.nextInt(answerWords.size()));
        this.answerWordArray = new Character[answerWord.length()];
        this.guessWordArray = new Character[answerWord.length()];

        this.gray = new boolean[answerWord.length()];
        this.yellow = new boolean[answerWord.length()];
        this.green = new boolean[answerWord.length()];

        this.grayLetters = new ArrayList<Character>();
        this.yellowLetters = new ArrayList<Character>();
        this.greenLetters = new ArrayList<Character>();

        this.numGuesses = 0;

        answerWordArray = getAnswerWordArray(answerWord);
    }

    //sets a specific answer word -- ONLY FOR TESTING
    public void setAnswerWord(String answerWord){
        this.answerWord = answerWord.toLowerCase();
        this.answerWordArray = getAnswerWordArray(this.answerWord);

        //reset the feedback arrays
        this.gray = new boolean[answerWord.length()];
        this.yellow = new boolean[answerWord.length()];
        this.green = new boolean[answerWord.length()];

        this.grayLetters = new ArrayList<>();
        this.yellowLetters = new ArrayList<>();
        this.greenLetters = new ArrayList<>();
    }

    //method to fill the answerWordArray with each character in answerWord
    public Character[] getAnswerWordArray(String answerWord) {
        Character[] answerWordArr = new Character[answerWord.length()];
        for (int i = 0; i < answerWord.length(); i++)
        {
            answerWordArr[i] = answerWord.charAt(i);
        }
        return answerWordArr;
    }

    //method to fill the guessWordArray with each character in guessWord
    public Character[] getGuessWordArray(String guessWord) {
        Character[] guessWordArr = new Character[guessWord.length()];
        for (int i = 0; i < guessWord.length(); i++)
        {
            guessWordArr[i] = guessWord.charAt(i);
        }
        return guessWordArr;
    }

    /** Compare answerWordArray and guessWordArray to fill the arrays grey, yellow, and green appropriately
     *
     *      - gray is true when a character in guessWordArray does not exist in answerWordArray
     *
     *      - yellow is true when a character in guessWordArray exists in answerWordArray, but at a different index
     *          Additional condition: the combined number of yellow and green instances of a letter must not exceed
     *          the number of times that character appears in answerWordArray. For example:
     *          answerWord = candy
     *          guessWord == manta
     *          The first "a" in manta should be green. The second "a" in manta should be grey, NOT yellow.
     *          The array usedCharacters is used to keep track of which characters in answerWordArray have
     *          already been used to ensure the correct number of characters are marked yellow.
     *
     *      - green is true when a character in guessWordArray exists in answerWordArray, and is at the same index
     * */
    public void guessAndAnswerComparison(String guessWord)
    {
        numGuesses++;

        guessWord = guessWord.toLowerCase();
        guessWordArray = getGuessWordArray(guessWord);

        //initialize all indices of grey to true, and all the indices of yellow and green to false
        for (int i = 0; i < answerWordArray.length; i++)
        {
            gray[i] = true;
            yellow[i] = false;
            green[i] = false;
        }

        //array to track which positions in answerWordArray are already used
        boolean[] usedCharacters = new boolean[answerWordArray.length];

        //first pass: mark applicable characters from answerWordArray as green
        for (int i = 0; i < answerWordArray.length; i++)
        {
            if (answerWordArray[i].equals(guessWordArray[i]))
            {
                green[i] = true;
                greenLetters.add(Character.toUpperCase(answerWordArray[i]));
                gray[i] = false; //set to false, so the UI knows to display green, not grey
                usedCharacters[i] = true; //this letter is now "used up"
            }
        }

        //second pass: mark applicable characters from answerWordArray as yellow
        for (int i = 0; i < answerWordArray.length; i++)
        {
            if (!green[i]) //skip over green positions
            {
                for (int j = 0; j < guessWordArray.length; j++)
                {
                    if (!usedCharacters[j] && answerWordArray[i].equals(guessWordArray[j]))
                    {
                        yellow[j] = true;
                        yellowLetters.add(Character.toUpperCase(answerWordArray[i]));
                        gray[j] = false; //set to false, so the UI knows to display yellow, not grey
                        usedCharacters[j] = true; //this letter is now "used up"
                        break;
                    }
                    // else it is gray, so add to gray letter list
                    grayLetters.add(Character.toUpperCase(guessWordArray[j]));
                }
            }
        }
    }

    //getters for arrays
    public boolean[] getGray() { return gray; }
    public boolean[] getYellow() { return yellow; }
    public boolean[] getGreen() { return green; }

    //getters for lists
    public List<Character> getGrayLetters() { return grayLetters; }
    public List<Character> getYellowLetters() { return yellowLetters; }
    public List<Character> getGreenLetters() { return greenLetters; }

    //answer getter
    public String getAnswerWord() {return answerWord;}

    //method to check what the state of the game is
    public String checkGameState() {
        boolean allGreen = true;
        for (boolean g : green) {
            if (!g) {
                allGreen = false;
                break;
            }
        }
        if (allGreen){
            return "WIN";
        }
        else if (numGuesses >= 6) {
            return "LOSE";
        }
        else {
            return "ONGOING";
        }
    }


}
