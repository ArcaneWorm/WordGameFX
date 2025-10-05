/**
 * GameApplication.java
 *
 * Acts as the bridge between the logic and the UI -- launches javaFX.
 */

package org.example.wordgamefx.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //setup UI
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("wordgame.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 570, 654);
        stage.setTitle("HalloWordle");
        stage.setScene(scene);
        stage.show();
    }
}