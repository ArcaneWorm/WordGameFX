module org.example.wordgamefx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens org.example.wordgamefx to javafx.fxml;
    exports org.example.wordgamefx;
    exports org.example.wordgamefx.controller;
    opens org.example.wordgamefx.controller to javafx.fxml;
    exports org.example.wordgamefx.view;
    opens org.example.wordgamefx.view to javafx.fxml;

}