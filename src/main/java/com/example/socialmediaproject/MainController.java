package com.example.socialmediaproject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import static javafx.application.Platform.exit;

public class MainController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onExit() {
        exit();
    }
}