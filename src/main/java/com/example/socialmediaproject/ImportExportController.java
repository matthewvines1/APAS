package com.example.socialmediaproject;

import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Scene;

public class ImportExportController {

    private ImportExportModel importExportModel;
    private FXMLLoader fxmlLoader;
    private Scene scene;

    public void ImportExportController(FXMLLoader fxmlLoader, Scene scene) {
        this.fxmlLoader = fxmlLoader;
        this.scene = scene;
        importExportModel = new ImportExportModel();
    }
}
