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

public class GameLogic
{
    private final String testWord = "candy";
    /*
    Other words:
    - witch
    - ghoul
    - mummy
    - demon
    - spook
    - ghost
    - eerie
    - haunt
    - crypt
    - scare
    - scary
    - bones
    - skull
    - fangs
    - blood
    - treat
    - trick
    - grave
     */

    //array to keep track of each character in the answer string
    private Character[] testWordArray = new Character[testWord.length()];

    //array to keep track of each character in the user-inputted guess string
    private Character[] guessWordArray = new Character[testWord.length()];

    //arrays to keep track of common characters and common character placements for testWordArray and guessWordArray
    //true when a character in guessWordArray does not exist in testWordArray
    private Boolean[] grey = new Boolean[testWord.length()];
    /* true when a character in guessWordArray exists in testWordArray, but at a different index

       Additional condition: the combined number of yellow and green instances of a letter must not exceed
       the number of times that character appears in testWordArray. For example:
       testWord = candy
       guessWord == manta
       The first "a" in manta should be green. The second "a" in manta should be grey, NOT yellow.
    */
    private Boolean[] yellow = new Boolean[testWord.length()];
    //true when a character in guessWordArray exists in testWordArray, and is at the same index
    private Boolean[] green = new Boolean[testWord.length()];

    //method to fill the testWordArray with each character in testWord
    public Character[] getTestWordArray(String testWord, Character[] testWordArray) {
        for (int i = 0; i < testWord.length(); i++)
        {
            testWordArray[i] = testWord.charAt(i);
        }
        return testWordArray;
    }

    //method to fill the guessWordArray with each character in guessWord
    public Character[] getGuessWordArray(String guessWord, Character[] guessWordArray) {
        for (int i = 0; i < guessWord.length(); i++)
        {
            guessWordArray[i] = guessWord.charAt(i);
        }
        return guessWordArray;
    }

    /** Compare testWordArray and guessWordArray to fill the arrays grey, yellow, and green appropriately
     *
     *      - grey is true when a character in guessWordArray does not exist in testWordArray
     *
     *      - yellow is true when a character in guessWordArray exists in testWordArray, but at a different index
     *          Additional condition: the combined number of yellow and green instances of a letter must not exceed
     *          the number of times that character appears in testWordArray. For example:
     *          testWord = candy
     *          guessWord == manta
     *          The first "a" in manta should be green. The second "a" in manta should be grey, NOT yellow.
     *          The array usedCharacters is used to keep track of which characters in testWordArray have
     *          already been used to ensure the correct number of characters are marked yellow.
     *
     *      - green is true when a character in guessWordArray exists in testWordArray, and is at the same index
     * */
    public void guessAndTestComparison(Character[] testWordArray, Character[] guessWordArray,
                                       boolean[] grey, boolean[] yellow, boolean[] green)
    {
        //initialize all indices of grey to true, and all the indices of yellow and green to false
        for (int i = 0; i < testWordArray.length; i++)
        {
            grey[i] = true;
            yellow[i] = false;
            green[i] = false;
        }

        //array to track which positions in testWordArray are already used
        boolean[] usedCharacters = new boolean[testWordArray.length];

        //first pass: mark applicable characters from testWordArray as green
        for (int i = 0; i < testWordArray.length; i++)
        {
            if (testWordArray[i].equals(guessWordArray[i]))
            {
                green[i] = true;
                grey[i] = false; //set to false, so the UI knows to display green, not grey
                usedCharacters[i] = true; //this letter is now "used up"
            }
        }

        //second pass: mark applicable characters from testWordArray as yellow
        for (int i = 0; i < testWordArray.length; i++)
        {
            if (!green[i]) //skip over green positions
            {
                for (int j = 0; j < guessWordArray.length; j++)
                {
                    if (!usedCharacters[j] && testWordArray[i].equals(guessWordArray[j]))
                    {
                        yellow[i] = true;
                        grey[i] = false; //set to false, so the UI knows to display yellow, not grey
                        usedCharacters[i] = true; //this letter is now "used up"
                        break;
                    }
                }
            }
        }
    }


    /*
    CODE NEXT:
    - logic for winning/loosing -- use an int guessCounter
    - input validation:
        - is guessWord in the dictionary? (should only use letters a-z, no special characters or numbers)
        - is guessWord the correct # of characters (5)?

     */



}
