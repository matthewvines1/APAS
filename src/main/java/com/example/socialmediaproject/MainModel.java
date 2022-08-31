package com.example.socialmediaproject;

import com.example.socialmediaproject.databaseentities.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.crypto.Cipher;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static javafx.application.Platform.exit;

public class MainModel {

    private final String ENCRYPTED_FILE_PATH = Global.PROGRAM_FILE_PATH + "\\EncryptedFiles";
    private final String DATABASE_URL_FILENAME = ENCRYPTED_FILE_PATH + "\\DatabaseURL";
    private final String DATABASE_USERNAME_FILENAME = ENCRYPTED_FILE_PATH + "\\DatabaseUsername";
    private final String DATABASE_PASSWORD_FILENAME = ENCRYPTED_FILE_PATH + "\\DatabasePassword";
    private final String LOGIN_TITLE = "APAS Login";
    private final String DATABASE_CREDENTIALS_TITLE = "Database Credentials";
    private final String CREATE_ACCOUNT_TITLE = "APAS Create Account";
    private char[] databaseUrl;
    private char[] databaseUsername;
    private char[] databasePassword;
    private char[] userUsername;
    private char[] userPasswordHash;
    User currentUser;
    Cipher[] ciphers;

    private ImportExportModel importExportModel;
    private EditContactsModel editContactsModel;
    private DatabaseConnector databaseConnector;
    private Stage loginStage;
    private Stage createAccountStage;
    private Stage databaseCredentialsStage;
    private Stage tempStage;

    public MainModel() {
        importExportModel = new ImportExportModel();
        editContactsModel = new EditContactsModel();
        databaseConnector =  new DatabaseConnector();
        //get buffered writers for each database parameter files, then read and decrypt and set in database connector
        //if they do not exist prompt the user to enter them, then encrypt and store in file paths
        openPopupLogin();
    }

    private void openPopupLogin() {
        final boolean[] isLoggingIn = {true};
        Scene loginScene = openPopup(LOGIN_TITLE, "login.fxml");
        TextField usernameTextField = (TextField) loginScene.lookup("#usernameTextField");
        TextField passwordTextField = (TextField) loginScene.lookup("#passwordTextField");
        TextField confirmPasswordTextField = (TextField) loginScene.lookup("#confirmPasswordTextField");
        confirmPasswordTextField.setVisible(false);
        loginStage = tempStage;
        Button loginButton = (Button) loginScene.lookup("#loginButton");
        Button redirectButton = (Button) loginScene.lookup("#redirectButton");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (userUsername.length == 0 || passwordTextField.getLength() == 0) {
                    showErrorMessage("Please Enter Username and Password");
                } else {
                    if (isLoggingIn[0]) {
                        userUsername = usernameTextField.getText().toCharArray();
                        userPasswordHash = CryptoWrapper.generateHash(userUsername, passwordTextField.getText().toCharArray());
                        if (!tryDatabaseConnection()) {
                            openPopupDatabaseCredentials();
                        } else {
                            startAfterAuthentication();
                        }
                    } else {
                        userUsername = passwordTextField.getText().toCharArray();
                        boolean isError = false;
                        if (passwordTextField.getLength() != confirmPasswordTextField.getLength()) {
                            isError = true;
                        } else {
                            for (int i = 0; i < passwordTextField.getLength(); i++) {
                                if(passwordTextField.getText().toCharArray()[i] != confirmPasswordTextField.getText().toCharArray()[i]) {
                                    isError = true;
                                }
                            }
                        }
                        if(isError) {
                            showErrorMessage("Passwords Do Not Match");
                        } else {
                            userPasswordHash = CryptoWrapper.generateHash(userUsername, passwordTextField.getText().toCharArray());
                            if (!tryDatabaseConnection()) {
                                openPopupDatabaseCredentials();
                            } else {
                                startAfterAuthentication();
                            }
                        }
                    }
                }
            }
        });
        redirectButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(isLoggingIn[0]) {
                    isLoggingIn[0] = false;
                    loginButton.setText("Create Account");
                    confirmPasswordTextField.setVisible(true);
                } else {
                    isLoggingIn[0] = true;
                    loginButton.setText("Log In");
                    confirmPasswordTextField.setVisible(false);
                }
            }
        });
    }

    private void openPopupDatabaseCredentials() {
        Scene databaseCredentialsScene = openPopup(DATABASE_CREDENTIALS_TITLE, "database-credentials.fxml");
        databaseCredentialsStage = tempStage;
    }

    private void startAfterAuthentication() {

    }

    private boolean tryDatabaseConnection() {
        setLocalDatabaseUrlByFile();
        setLocalDatabaseUsernameByFile();
        setLocalDatabasePasswordByFile();
        return createDatabaseConnection();
    }

    private void setLocalDatabaseUrlByFile() {
        databaseUrl = CryptoWrapper.getPlainText(ciphers[1], FileTools.getCharArrayByFile(DATABASE_URL_FILENAME));
    }

    private void setLocalDatabaseUsernameByFile() {
        databaseUsername = CryptoWrapper.getPlainText(ciphers[1], FileTools.getCharArrayByFile(DATABASE_USERNAME_FILENAME));
    }

    private void setLocalDatabasePasswordByFile() {
        databasePassword = CryptoWrapper.getPlainText(ciphers[1], FileTools.getCharArrayByFile(DATABASE_PASSWORD_FILENAME));
    }

    public void setLocalAndFileDatabase(char[] databaseUrl, char[] databaseUsername, char[] databasePassword) {
        this.databaseUrl = databaseUrl;
        this.databaseUsername = databaseUsername;
        this.databasePassword = databasePassword;
        FileTools.setFileByCharArray(DATABASE_URL_FILENAME, CryptoWrapper.getCipherText(ciphers[0], databaseUrl));
        FileTools.setFileByCharArray(DATABASE_USERNAME_FILENAME, CryptoWrapper.getCipherText(ciphers[0], databaseUsername));
        FileTools.setFileByCharArray(DATABASE_PASSWORD_FILENAME, CryptoWrapper.getCipherText(ciphers[0], databasePassword));
        createDatabaseConnection();
    }

    public boolean createDatabaseConnection() {
        if (databaseUrl != null && databaseUsername != null && databasePassword != null) {
            databaseConnector.setConnection(databaseUrl, databaseUsername, databasePassword);
            Global.clearChars(databaseUrl);
            Global.clearChars(databaseUsername);
            Global.clearChars(databasePassword);
            ciphers = CryptoWrapper.getCipher(userPasswordHash);
            currentUser = databaseConnector.getUser(userUsername, userPasswordHash);
            if(currentUser == null) {
                databaseConnector.setUser(new User(userUsername, userPasswordHash, false, false,
                        false, true, new Date(System.currentTimeMillis()),
                        new Time(System.currentTimeMillis()), new Date(System.currentTimeMillis()),
                        new Time(System.currentTimeMillis())));
                currentUser = databaseConnector.getUser(userUsername, userPasswordHash);
            }
            return currentUser != null;
        }
        return false;
    }

    protected ImportExportModel getImportExportModel() {
        return importExportModel;
    }

    protected EditContactsModel getEditContactsModel() {
        return editContactsModel;
    }
    protected void importContactsVCard() {

    }

    protected void importContactsCSV() {

    }

    protected void importInvoicesCSV() {

    }

    protected void importAllFilesZIP () {

    }

    protected void importContactsCloud () {

    }

    protected void importInvoicesCloud () {

    }

    protected void importAllFilesCloud () {

    }

    protected void exportContactsVCard() {

    }

    protected void exportContactsCSV() {

    }

    protected void exportInvoicesCSV() {

    }

    protected void exportAllFilesZIP () {

    }

    protected void exportContactsCloud () {

    }

    protected void exportInvoicesCloud () {

    }

    protected void exportAllFilesCloud () {

    }

    protected void editContacts () {

    }

    protected void about () {

    }

    protected void exitProgram() {
        exit();
    }

    private void showErrorMessage(String message) {

    }

    private Scene openPopup(String title, String fxml) {
        FXMLLoader loader;
        Parent loginRoot;
        Stage loginStage;
        try {
            loader = new FXMLLoader(getClass().getResource(fxml));
            loginRoot = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        loginStage = new Stage();
        loginStage.setTitle(title);
        loginStage.getIcons().add(Global.LOGO);
        loginStage.initModality(Modality.APPLICATION_MODAL);
        tempStage = loginStage;
        return new Scene(loginRoot, Global.DEFAULT_POPUP_WIDTH, Global.DEFAULT_POPUP_HEIGHT);
    }

}
