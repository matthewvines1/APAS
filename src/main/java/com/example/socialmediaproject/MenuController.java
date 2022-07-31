package com.example.socialmediaproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MenuController {

    private final String IMPORT_EXPORT_TITLE = "Import/Export";

    //Import Export Variables
    private Stage importExportPopupStage;
    private Scene importExportScene;
    private Parent importExportRoot;
    private ImportExportController importExportController;

    private SingleSelectionModel<Tab> selectionModel1;
    private SingleSelectionModel<Tab> selectionModel2;
    private SingleSelectionModel<Tab> selectionModel3;
    private SingleSelectionModel<Tab> selectionModel4;
    private SingleSelectionModel<Tab> selectionModel5;
    private SingleSelectionModel<Tab> selectionModel6;
    private SingleSelectionModel<Tab> selectionModel7;
    @FXML
    Pane contentArea;

    //State variables
    private final int STATE_COUNT = 20;
    private int currentState;
    private ArrayList<MainModel> stateList;
    private MainModel mainModel;

    public MenuController() {
        mainModel = new MainModel();
        //Init Import Export
        FXMLLoader loader;
        try {
            loader = new FXMLLoader(getClass().getResource("import-export-window.fxml"));
            importExportRoot = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        importExportController = loader.getController();
        importExportPopupStage = new Stage();
        importExportPopupStage.setTitle(IMPORT_EXPORT_TITLE);
        importExportPopupStage.getIcons().add(GlobalVariables.LOGO);
        importExportScene = new Scene(importExportRoot, GlobalVariables.DEFAULT_POPUP_WIDTH, GlobalVariables.DEFAULT_POPUP_HEIGHT);
        selectionModel1 = ((TabPane) importExportScene.lookup("#mainTabPane")).getSelectionModel();
        selectionModel2 = ((TabPane) importExportScene.lookup("#importTabPane")).getSelectionModel();
        selectionModel3 = ((TabPane) importExportScene.lookup("#importInternalTabPane")).getSelectionModel();
        selectionModel4 = ((TabPane) importExportScene.lookup("#importExternalTabPane")).getSelectionModel();
        selectionModel5 = ((TabPane) importExportScene.lookup("#exportTabPane")).getSelectionModel();
        selectionModel6 = ((TabPane) importExportScene.lookup("#exportInternalTabPane")).getSelectionModel();
        selectionModel7 = ((TabPane) importExportScene.lookup("#exportExternalTabPane")).getSelectionModel();
        //Init Edit Contacts
    }

    private void openInternalExternalPopup(int paneSelection1, int paneSelection2, int paneSelection3) {
        selectionModel1.select(paneSelection1);
        switch(paneSelection1){
            case 0:
                selectionModel2.select(paneSelection2);
                switch(paneSelection2) {
                    case 0:
                        selectionModel3.select(paneSelection3);
                        break;
                    case 1:
                        selectionModel4.select(paneSelection3);
                        break;
                }
                break;
            case 1:
                selectionModel5.select(paneSelection2);
                switch(paneSelection2) {
                    case 0:
                        selectionModel6.select(paneSelection3);
                        break;
                    case 1:
                        selectionModel7.select(paneSelection3);
                        break;
                }
                break;
        }
        importExportController.menuChange();
        importExportPopupStage.setScene(importExportScene);
        importExportPopupStage.initModality(Modality.APPLICATION_MODAL);
        importExportPopupStage.show();
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
