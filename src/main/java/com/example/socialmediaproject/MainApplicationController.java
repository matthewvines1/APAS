package com.example.socialmediaproject;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainApplicationController extends Application implements Initializable {

    //State variables
    private final int STATE_COUNT = 20;
    private int currentState;
    private ArrayList<MainModel> stateList;
    //Model variables
    private MainModel mainModel;
    //UI variables
    private Stage stage;
    private FXMLLoader fxmlLoader;
    private Scene scene;
    private Image logo;

    @Override
    public void start(Stage stage) throws IOException {
        //Initialize variables
        currentState = 0;
        stateList = new ArrayList<>();
        mainModel = new MainModel();
        this.stage = stage;
        logo = new Image(MainApplicationController.class.getResourceAsStream("logo.png"));
        //Start UI
        fxmlLoader = new FXMLLoader(MainApplicationController.class.getResource("main-menu-wrapper.fxml"));
        scene = new Scene(fxmlLoader.load(), 960, 600);
        stage.setTitle("APAS");
        stage.getIcons().add(logo);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    protected void importContactsVCard(javafx.event.ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("import-export-window.fxml"));
        MenuBar menuBar = (MenuBar)stage.getScene().lookup("myMenuBar");
        Scene newScene = new Scene(loader);
        Stage newStage = (Stage) menuBar.getScene().getWindow();
        newStage.setScene(newScene);
        newStage.show();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}