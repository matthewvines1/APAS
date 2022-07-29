package com.example.socialmediaproject;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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

    private void openInternalExternalPopup(int paneSelection1, int paneSelection2, int paneSelection3) {
        try {
            root = FXMLLoader.load(getClass().getResource("import-export-window.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage popupStage = new Stage();
        popupStage.setTitle(IMPORT_EXPORT_TITLE);
        popupStage.getIcons().add(GlobalVariables.LOGO);
        Scene popupScene = new Scene(root, GlobalVariables.DEFAULT_POPUP_WIDTH, GlobalVariables.DEFAULT_POPUP_HEIGHT);
        String tabPaneName1 = "#mainTabPane";
        String tabPaneName2 = "";
        String tabPaneName3 = "";
        switch (paneSelection1) {
            case 0:
                tabPaneName2 = "#importTabPane";
                switch (paneSelection2) {
                    case 0:
                        tabPaneName3 = "#importInternalTabPane";

                        break;
                    case 1:
                        tabPaneName3 = "#importExternalTabPane";
                        break;
                }
                break;
            case 1:
                tabPaneName2 = "#exportTabPane";
                switch (paneSelection2) {
                    case 0:
                        tabPaneName3 = "#exportInternalTabPane";
                        break;
                    case 1:
                        tabPaneName3 = "#exportExternalTabPane";
                        break;
                }
                break;
        }
        TabPane tabPane1 = (TabPane) popupScene.lookup(tabPaneName1);
        TabPane tabPane2 = (TabPane) popupScene.lookup(tabPaneName2);
        TabPane tabPane3 = (TabPane) popupScene.lookup(tabPaneName3);
        SingleSelectionModel<Tab> selectionModel1 = tabPane1.getSelectionModel();
        SingleSelectionModel<Tab> selectionModel2 = tabPane2.getSelectionModel();
        SingleSelectionModel<Tab> selectionModel3 = tabPane3.getSelectionModel();
        selectionModel1.select(paneSelection1);
        selectionModel2.select(paneSelection2);
        selectionModel3.select(paneSelection3);
        popupStage.setScene(popupScene);
        popupStage.show();
    }

    @FXML
    public void importContactsVCard() {
        openInternalExternalPopup(0, 0, 0);
    }

    @FXML
    protected void importContactsCSV() {
        openInternalExternalPopup(0, 0, 1);
    }

    @FXML
    protected void importInvoicesCSV() {
        openInternalExternalPopup(0, 0, 2);
    }

    @FXML
    protected void importAllFilesZIP () {
        openInternalExternalPopup(0, 0, 3);
    }

    @FXML
    protected void importContactsCloud () {
        openInternalExternalPopup(0, 1, 0);
    }

    @FXML
    protected void importInvoicesCloud () {
        openInternalExternalPopup(0, 1, 1);
    }

    @FXML
    protected void importAllFilesCloud () {
        openInternalExternalPopup(0, 1, 2);
    }

    @FXML
    protected void exportContactsVCard() {
        openInternalExternalPopup(1, 0, 0);
    }

    @FXML
    protected void exportContactsCSV() {
        openInternalExternalPopup(1, 0, 1);
    }

    @FXML
    protected void exportInvoicesCSV() {
        openInternalExternalPopup(1, 0, 2);
    }

    @FXML
    protected void exportAllFilesZIP () {
        openInternalExternalPopup(1, 0, 3);
    }

    @FXML
    protected void exportContactsCloud () {
        openInternalExternalPopup(1, 1, 0);
    }

    @FXML
    protected void exportInvoicesCloud () {
        openInternalExternalPopup(1, 1, 1);
    }

    @FXML
    protected void exportAllFilesCloud () {
        openInternalExternalPopup(1, 1, 2);
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
