/**
 * GameController.java
 *
 * Coordinates between user interaction and game logic:
 *      - Receives input from the user via the console or UI
 *      - Passes guesses onto GameLogic.java for validation and evaluation
 *      - Gets results from GameLogic.java and displays those in the console or UI
 *
 */

package org.example.wordgamefx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import org.example.wordgamefx.model.GameLogic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class GameController
{
    //will store all valid 5 letter guess words
    private Set<String> dictionary;

    //input fields
    @FXML
    private TextField inputBox;
    @FXML
    private Button confirmBtn;

    //guesses
    @FXML
    private HBox guess1;
    @FXML
    private HBox guess2;
    @FXML
    private HBox guess3;
    @FXML
    private HBox guess4;
    @FXML
    private HBox guess5;
    @FXML
    private HBox guess6;

    private HBox[] guessRows;

    //keyboard display
    @FXML
    private HBox keyboardr1;
    @FXML
    private HBox keyboardr2;
    @FXML
    private HBox keyboardr3;

    //keep count of guesses
    int guesses = 0;

    private GameLogic logic;

    @FXML
    private void initialize()
    {
        guessRows = new HBox[] {guess1, guess2, guess3, guess4, guess5, guess6};
        //initialize game with word
        //later add way to randomize word
        logic = new GameLogic();

        //load dictionary
        try
        {
            InputStream in = getClass().getResourceAsStream("/wordle-allowed-guesses-updated.txt");
            dictionary = new HashSet<>();
            new BufferedReader(new InputStreamReader(in))
                    .lines()
                    .map(String::trim)
                    .filter(w -> w.length() == 5)
                    .forEach(dictionary::add);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            dictionary = new HashSet<>();
        }
    }
    @FXML
    private void handleConfirmedInput(ActionEvent e)
    {
        String input = inputBox.getText().toLowerCase();

        //before processing the user's guess, make sure it's in the dictionary
        if(!dictionary.contains(input))
        {
            //show alert if invalid word
            Alert alert =  new Alert(Alert.AlertType.ERROR, "Not a valid word!");
            alert.showAndWait();
            return;
        }

        //add method to validate guess
        //if invalid guess then have user re-enter guess
        //otherwise increment counter and handle input
        Label[] letters = guessRows[guesses].getChildren().toArray(new Label[0]);

        boolean[] gray = logic.getGray();
        boolean[] yellow = logic.getYellow();
        boolean[] green = logic.getGreen();

        logic.guessAndAnswerComparison(input);

        //update UI
        for(int i = 0; i < input.length(); i++){
            letters[i].setText(String.valueOf(input.charAt(i)).toUpperCase());

            if(green[i]){
                letters[i].setStyle("-fx-background-color: #96CA2D; -fx-text-fill: white; -fx-border-color: #D9D9D9;");
            }
            else if(yellow[i]){
                letters[i].setStyle("-fx-background-color: #FFBE00; -fx-text-fill: white; -fx-border-color: #D9D9D9;");
            }
            else{
                letters[i].setStyle("-fx-background-color: #424242; -fx-text-fill: white; -fx-border-color: #D9D9D9;");
            }
        }
        guesses++;

        //update keyboard display()
        updateKeyboard();

    }

    @FXML
    private void updateKeyboard(){
        List<Character> grayLets = logic.getGrayLetters();
        List<Character> yellowLets = logic.getYellowLetters();
        List<Character> greenLets = logic.getGreenLetters();

        Label[] row1Letters = keyboardr1.getChildren().toArray(new Label[0]);
        Label[] row2Letters = keyboardr2.getChildren().toArray(new Label[0]);
        Label[] row3Letters = keyboardr3.getChildren().toArray(new Label[0]);

        colorKeyboardRow(row1Letters, grayLets, yellowLets, greenLets);
        colorKeyboardRow(row2Letters, grayLets, yellowLets, greenLets);
        colorKeyboardRow(row3Letters, grayLets, yellowLets, greenLets);
    }

    private void colorKeyboardRow(Label[] rowLetters, List<Character> grayLets, List<Character> yellowLets, List<Character> greenLets){
        for(int i = 0; i < rowLetters.length; i++) {
            char letter = rowLetters[i].getText().charAt(0);
            if (greenLets.contains(letter)) {
                rowLetters[i].setStyle("-fx-background-color: #96CA2D; -fx-text-fill: white; -fx-border-color: #D9D9D9;");
            }
            else if (yellowLets.contains(letter)) {
                rowLetters[i].setStyle("-fx-background-color: #FFBE00; -fx-text-fill: white; -fx-border-color: #D9D9D9;");
            }
            else if (grayLets.contains(letter)) {
                rowLetters[i].setStyle("-fx-background-color: #424242; -fx-text-fill: white; -fx-border-color: #D9D9D9;");
            }
            }
        }
    }
