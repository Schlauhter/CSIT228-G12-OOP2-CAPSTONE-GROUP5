package com.example.budgettracker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/budgettracker/fxml/Login.fxml"));
        Scene scene = new Scene(loader.load(), 440, 340);
        stage.setTitle("Budget Tracker");
        stage.setResizable(false);

        var iconStream = getClass().getResourceAsStream("/com/example/budgettracker/fxml/budget.png");
        if (iconStream != null) {
            stage.getIcons().add(new javafx.scene.image.Image(iconStream));
        }

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
