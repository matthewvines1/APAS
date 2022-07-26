package com.example.socialmediaproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    //UI variables
    Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        //Initialize variables
        this.stage = stage;
        Image logo = new Image(Main.class.getResourceAsStream("logo.png"));
        //Start UI
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-menu-wrapper.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 960, 600);
        stage.setTitle("APAS");
        stage.getIcons().add(logo);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}