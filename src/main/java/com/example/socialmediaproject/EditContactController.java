package com.example.socialmediaproject;

import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Scene;

public class EditContactController {

    private EditContactsModel editContactsModel;
    private FXMLLoader fxmlLoader;
    private Scene scene;

    public void EditContactController(FXMLLoader fxmlLoader, Scene scene) {
        this.fxmlLoader = fxmlLoader;
        this.scene = scene;
        editContactsModel = new EditContactsModel();
    }
}
