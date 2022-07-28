package com.example.socialmediaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MenuController {

    private final String IMPORT_EXPORT_TITLE = "Import/Export";

    //Scene variables
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    Pane contentArea;

    //State variables
    private final int STATE_COUNT = 20;
    private int currentState;
    private ArrayList<MainModel> stateList;
    private MainModel mainModel;

    @FXML
    public void importContactsVCard(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("import-export-window.fxml"));
        Stage popupStage = new Stage();
        popupStage.setTitle(IMPORT_EXPORT_TITLE);
        popupStage.getIcons().add(GlobalVariables.LOGO);
        popupStage.setScene(new Scene(root, GlobalVariables.DEFAULT_POPUP_WIDTH, GlobalVariables.DEFAULT_POPUP_HEIGHT));
        popupStage.show();
    }

    @FXML
    protected void importContactsCSV() {
        mainModel.importContactsCSV();
    }

    @FXML
    protected void importInvoicesCSV() {
        mainModel.importInvoicesCSV();
    }

    @FXML
    protected void importAllFilesZIP () {
        mainModel.importAllFilesZIP();
    }

    @FXML
    protected void importContactsCloud () {
        mainModel.importContactsCloud();
    }

    @FXML
    protected void importInvoicesCloud () {
        mainModel.importInvoicesCloud();
    }

    @FXML
    protected void importAllFilesCloud () {
        mainModel.importAllFilesCloud();
    }

    @FXML
    protected void exportContactsVCard() {
        mainModel.exportContactsVCard();
    }

    @FXML
    protected void exportContactsCSV() {
        mainModel.exportContactsCSV();
    }

    @FXML
    protected void exportInvoicesCSV() {
        mainModel.exportInvoicesCSV();
    }

    @FXML
    protected void exportAllFilesZIP () {
        mainModel.exportAllFilesZIP();
    }

    @FXML
    protected void exportContactsCloud () {
        mainModel.exportContactsCloud();
    }

    @FXML
    protected void exportInvoicesCloud () {
        mainModel.exportInvoicesCloud();
    }

    @FXML
    protected void exportAllFilesCloud () {
        mainModel.exportAllFilesCloud();
    }

    @FXML
    protected void undo () {
        if(currentState > 0) {
            currentState -= 1;
            mainModel = stateList.get(currentState);
        }
    }

    @FXML
    protected void redo () {
        if(currentState < STATE_COUNT) {
            currentState += 1;
            mainModel = stateList.get(currentState);
        }
    }

    @FXML
    protected void editContacts () {
        mainModel.editContacts();
    }

    @FXML
    protected void about () {
        mainModel.about();
    }

    @FXML
    protected void onExit() {
        mainModel.exitProgram();
    }

    private void saveState() {
        while(stateList.size()-1 > currentState) {
            stateList.remove(stateList.size() - 1);
        }
        stateList.add(mainModel);
        currentState += 1;
        while(stateList.size() > STATE_COUNT) {
            stateList.remove(0);
            currentState = STATE_COUNT;
        }
    }
}
