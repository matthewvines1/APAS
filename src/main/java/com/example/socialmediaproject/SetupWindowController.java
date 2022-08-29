package com.example.socialmediaproject;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SetupWindowController {

    private char[] username;
    private char[] password;
    private char[] confirmPassword;
    private char[] mySqlUrl;
    private char[] mySqlUsername;
    private char[] mySqlPassword;

    @FXML
    TextField textFieldUsername;
    @FXML
    TextField textFieldPassword;
    @FXML
    TextField textFieldConfirmPassword;
    @FXML
    TextField textFieldMySqlUrl;
    @FXML
    TextField textFieldMySqlUsername;
    @FXML
    TextField textFieldMySqlPassword;
    @FXML
    Button buttonSubmit;

    @FXML
    public void initialize() {
        buttonSubmit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
    }
}
