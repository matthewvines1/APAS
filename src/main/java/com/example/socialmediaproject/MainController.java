package com.example.socialmediaproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.util.ArrayList;

public class MainController {

    private final int STATE_COUNT = 20;
    private int currentState = 0;
    private ArrayList<MenuModel> stateList = new ArrayList<>();
    private MenuModel menuModel = new MenuModel();


    @FXML
    protected void importContactsVCard() {
        menuModel.importContactsVCard();
    }

    @FXML
    protected void importContactsCSV() {
        menuModel.importContactsCSV();
    }

    @FXML
    protected void importInvoicesCSV() {
        menuModel.importInvoicesCSV();
    }

    @FXML
    protected void importAllFilesZIP () {
        menuModel.importAllFilesZIP();
    }

    @FXML
    protected void importContactsCloud () {
        menuModel.importContactsCloud();
    }

    @FXML
    protected void importInvoicesCloud () {
        menuModel.importInvoicesCloud();
    }

    @FXML
    protected void importAllFilesCloud () {
        menuModel.importAllFilesCloud();
    }

    @FXML
    protected void exportContactsVCard() {
        menuModel.exportContactsVCard();
    }

    @FXML
    protected void exportContactsCSV() {
        menuModel.exportContactsCSV();
    }

    @FXML
    protected void exportInvoicesCSV() {
        menuModel.exportInvoicesCSV();
    }

    @FXML
    protected void exportAllFilesZIP () {
        menuModel.exportAllFilesZIP();
    }

    @FXML
    protected void exportContactsCloud () {
        menuModel.exportContactsCloud();
    }

    @FXML
    protected void exportInvoicesCloud () {
        menuModel.exportInvoicesCloud();
    }

    @FXML
    protected void exportAllFilesCloud () {
        menuModel.exportAllFilesCloud();
    }

    @FXML
    protected void undo () {
        if(currentState > 0) {
            currentState -= 1;
            menuModel = stateList.get(currentState);
        }
    }

    @FXML
    protected void redo () {
        if(currentState < STATE_COUNT) {
            currentState += 1;
            menuModel = stateList.get(currentState);
        }
    }

    @FXML
    protected void editContacts () {
        menuModel.editContacts();
    }

    @FXML
    protected void about () {
        menuModel.about();
    }

    @FXML
    protected void onExit() {
        menuModel.exitProgram();
    }

    private void saveState() {
        while(stateList.size()-1 > currentState) {
            stateList.remove(stateList.size() - 1);
        }
        stateList.add(menuModel);
        currentState += 1;
        while(stateList.size() > STATE_COUNT) {
            stateList.remove(0);
            currentState = STATE_COUNT;
        }
    }
}