# Project Report

## Design Decisions

### Architecture
* Within the java folder in main, we created separate packages for Model, View, and Controller, to hold GameLogic.java, GameApplication.java, and GameController.java, respectively. 
* GameLogic.java contains all of the rules and mechanics of the game.
* GameApplication.java launches the UI.
* GameController.java coordinates between the UI and GameLogic.java.
- Why did you choose Swing vs JavaFX?

### Data Structures
We represented the game state as the three strings: “WIN”, “LOSE”, and “ONGOING”, keeping our code readable and concise.

### Algorithms
One key method we implemented was guessAndAnswerComparison(String guessWord), which compares the letters and letter placement of the user’s guess word with that of the chosen answer word. 

## Challenges Faced
1. **Challenge 1:** We used a .txt file listing thousands of dictionary words in conjunction with a hash set to validate user guesses. However, many common words were absent from the .txt file, which caused words which should have been considered valid to be rejected. 
   - **Solution:** We combined several .txt files containing valid 5-letter words, deleting duplicates, to create a larger and more comprehensive dictionary for validating user guesses. 
   
2. **Challenge 2:** A repeated, misplaced character in a user’s guess should only be marked “yellow” for the number of times that letter appears in the answer word. For example, a guess of “tummy” with the answer word “masks” should result in the first ‘m’ in “tummy” being marked yellow, and the second being marked gray. 
   - **Solution:** We created an array of “used” characters to keep track of which letters had already been accounted for. 

## What We Learned
* In GameController.java, we instantiated a GameLogic object, which uses the object-oriented concept of abstraction by hiding the contents of GameLogic.java while still using its methods. 
* The JUnit tests helped us observe our code under different conditions, and fine-tune any inconsistencies.

## If We Had More Time
* We would make each letter on the UI keyboard clickable to make our game more accessible and intuitive.
* We would add a replay button to allow for continuous gameplay.
