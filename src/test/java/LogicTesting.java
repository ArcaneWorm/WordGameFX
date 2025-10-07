import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.example.wordgamefx.model.GameLogic;


public class LogicTesting
{
    @Test
    //makes sure non-matching letters are gray
    public void testGrayLetters()
    {
        GameLogic logic = new GameLogic();

        //force the answer word for testing
        logic.setAnswerWord("candy");

        logic.guessAndAnswerComparison("ghost");

        boolean[] gray = logic.getGray();

        assertTrue(gray[0]);
        assertTrue(gray[1]);
        assertTrue(gray[2]);
        assertTrue(gray[3]);
        assertTrue(gray[4]);
    }

    @Test
    //makes sure matching letters in the wrong positon are yellow
    public void testYellowLetters()
    {
        GameLogic logic = new GameLogic();

        //force the answer word for testing
        logic.setAnswerWord("candy");

        logic.guessAndAnswerComparison("drain");

        boolean[] yellow = logic.getYellow();

        assertTrue(yellow[0]);
        assertTrue(yellow[2]);
        assertTrue(yellow[4]);
    }

    @Test
    //makes sure matching letters are green
    public void testGreenLetters()
    {
        GameLogic logic = new GameLogic();

        //force the answer word for testing
        logic.setAnswerWord("candy");

        logic.guessAndAnswerComparison("candy");

        boolean[] green = logic.getGreen();

        assertTrue(green[0]);
        assertTrue(green[1]);
        assertTrue(green[2]);
        assertTrue(green[3]);
        assertTrue(green[4]);
    }

    @Test
    //makes sure that GameLogic.java converts user inputs to lowercase
    public void testUppercaseLetters()
    {
        GameLogic logic = new GameLogic();

        //force the answer word for testing
        logic.setAnswerWord("candy");

        logic.guessAndAnswerComparison("CANDY");

        boolean[] green = logic.getGreen();

        assertTrue(green[0]);
        assertTrue(green[1]);
        assertTrue(green[2]);
        assertTrue(green[3]);
        assertTrue(green[4]);
    }

    @Test
    //makes sure that repeated green letters are counted
    public void testRepeatLetters()
    {
        GameLogic logic = new GameLogic();

        //force the answer word for testing
        logic.setAnswerWord("spook");

        logic.guessAndAnswerComparison("stool");

        boolean[] green = logic.getGreen();

        assertTrue(green[2]);
        assertTrue(green[3]);
    }

}
