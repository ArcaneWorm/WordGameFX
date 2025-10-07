import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.example.wordgamefx.model.GameLogic;


public class LogicTesting
{
    //makes sure non-matching letters are gray
    @Test
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

    //makes sure matching letters in the wrong positon are yellow
    @Test
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

    //makes sure matching letters are green
    @Test
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

    //makes sure that GameLogic.java converts user inputs to lowercase
    @Test
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

    //makes sure that repeated green letters are counted
    @Test
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

    //makes sure that a correct guess results in a game state of "WIN"
    @Test
    public void testWinState()
    {
        GameLogic logic = new GameLogic();

        logic.setAnswerWord("crypt");
        logic.guessAndAnswerComparison("crypt"); //correct guess

        String state = logic.checkGameState();
        assertEquals("WIN", state);

    }

    //makes sure that 6 incorrect guesses results in a game state of "LOSE"
    @Test
    public void testLoseState()
    {
        GameLogic logic = new GameLogic();
        logic.setAnswerWord("crypt");

        for (int i = 0; i < 6; i++)
        {
            logic.guessAndAnswerComparison("ghost"); //wrong guess
        }

        String state = logic.checkGameState();
        assertEquals("LOSE", state);

    }

    //makes sure that just one incorrect guess results in a game state of "ONGOING"
    @Test
    public void testOngoingState()
    {
        GameLogic logic = new GameLogic();

        logic.setAnswerWord("crypt");
        logic.guessAndAnswerComparison("spool");

        String state = logic.checkGameState();
        assertEquals("ONGOING", state);

    }

}
