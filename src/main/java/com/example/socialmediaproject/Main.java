package com.example.socialmediaproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private final String APPLICATION_NAME = "APAS";

    //UI variables
    Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        //Initialize variables
        this.stage = stage;
        //Start UI
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-menu-wrapper.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), GlobalVariables.DEFAULT_WINDOW_WIDTH, GlobalVariables.DEFAULT_WINDOW_HEIGHT);
        stage.setTitle(APPLICATION_NAME);
        stage.getIcons().add(GlobalVariables.LOGO);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}