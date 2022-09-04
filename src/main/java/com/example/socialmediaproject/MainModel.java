package com.example.socialmediaproject;

import com.example.socialmediaproject.databaseentities.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.crypto.Cipher;

import java.io.IOException;
import java.sql.Array;
import java.sql.Date;
import java.sql.Time;
import java.util.Arrays;

import static javafx.application.Platform.exit;

public class MainModel {

    private final String ENCRYPTED_FILE_PATH = Global.PROGRAM_FILE_PATH + "\\EncryptedFiles";
    private final String DATABASE_URL_FILENAME = "DatabaseURL";
    private final String DATABASE_USERNAME_FILENAME = "DatabaseUsername";
    private final String DATABASE_PASSWORD_FILENAME = "DatabasePassword";
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
        TextField usernameTextField = (TextField) loginScene.lookup("#UsernameTextField");
        TextField passwordTextField = (TextField) loginScene.lookup("#PasswordTextField");
        TextField confirmPasswordTextField = (TextField) loginScene.lookup("#ConfirmPasswordTextField");
        Label confirmPasswordLabel = (Label) loginScene.lookup("#ConfirmPasswordLabel");
        confirmPasswordTextField.setVisible(false);
        confirmPasswordLabel.setVisible(false);
        loginStage = tempStage;
        Button loginButton = (Button) loginScene.lookup("#SubmitButton");
        Button redirectButton = (Button) loginScene.lookup("#RedirectButton");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (usernameTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
                    showErrorMessage("Please Enter Username and Password");
                } else {
                    if (isLoggingIn[0]) {
                        userUsername = usernameTextField.getText().toLowerCase().toCharArray();
                        userPasswordHash = CryptoWrapper.generateHash(userUsername, passwordTextField.getText().
                                toCharArray());
                        ciphers = CryptoWrapper.getCipher(userPasswordHash);
                        if (!tryDatabaseConnection()) {
                            openPopupDatabaseCredentials();
                        } else {
                            startAfterAuthentication();
                        }
                    } else {
                        userUsername = usernameTextField.getText().toLowerCase().toCharArray();
                        boolean isError = false;
                        if (passwordTextField.getLength() != confirmPasswordTextField.getLength()) {
                            isError = true;
                        } else {
                            for (int i = 0; i < passwordTextField.getLength(); i++) {
                                if(passwordTextField.getText().toCharArray()[i] != confirmPasswordTextField.getText().
                                        toCharArray()[i]) {
                                    isError = true;
                                }
                            }
                        }
                        if(isError) {
                            showErrorMessage("Passwords Do Not Match");
                        } else {
                            userPasswordHash = CryptoWrapper.generateHash(userUsername, passwordTextField.getText().
                                    toCharArray());
                            ciphers = CryptoWrapper.getCipher(userPasswordHash);
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
                    redirectButton.setText("Go To Log In");
                    confirmPasswordTextField.setVisible(true);
                    confirmPasswordLabel.setVisible(true);
                } else {
                    isLoggingIn[0] = true;
                    loginButton.setText("Log In");
                    redirectButton.setText("Go To Create Account");
                    confirmPasswordTextField.setVisible(false);
                    confirmPasswordLabel.setVisible(false);
                }
            }
        });
        loginStage.setScene(loginScene);
        loginStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
        loginStage.setResizable(false);
        loginStage.showAndWait();
    }

    private void openPopupDatabaseCredentials() {
        Scene databaseCredentialsScene = openPopup(DATABASE_CREDENTIALS_TITLE, "database-credentials.fxml");
        databaseCredentialsStage = tempStage;
        Button submitButton = (Button) databaseCredentialsScene.lookup("#SubmitButton");
        Button backToAuthenticationButton = (Button) databaseCredentialsScene.lookup("#BackToAuthenticationButton");
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(setLocalAndFileDatabase(((TextField) databaseCredentialsScene.lookup("#DatabaseUrlTextField")).
                        getText().toCharArray(), ((TextField) databaseCredentialsScene.
                        lookup("#DatabaseUsernameTextField")).getText().toCharArray(), ((TextField)
                        databaseCredentialsScene.lookup("#DatabasePasswordTextField")).getText().toCharArray())) {
                    startAfterAuthentication();
                    databaseCredentialsStage.close();
                } else {
                    showErrorMessage("Database Connection Failed. Check Username, Password, Database URL, " +
                            "Database Username, and/or Database Password.");
                }
            }
        });
        backToAuthenticationButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                databaseCredentialsStage.close();
            }
        });
        databaseCredentialsStage.setScene(databaseCredentialsScene);
        databaseCredentialsStage.setResizable(false);
        databaseCredentialsStage.showAndWait();
    }

    private void startAfterAuthentication() {
        loginStage.close();
        System.out.println("Success!");
    }

    private boolean tryDatabaseConnection() {
        setLocalDatabaseUrlByFile();
        setLocalDatabaseUsernameByFile();
        setLocalDatabasePasswordByFile();
        boolean isSuccess = false;
        try {
            isSuccess = createDatabaseConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    private void setLocalDatabaseUrlByFile() {
        databaseUrl = CryptoWrapper.getPlainText(ciphers[1], FileTools.getCharArrayByFile(ENCRYPTED_FILE_PATH, DATABASE_URL_FILENAME));
    }

    private void setLocalDatabaseUsernameByFile() {
        databaseUsername = CryptoWrapper.getPlainText(ciphers[1], FileTools.getCharArrayByFile(ENCRYPTED_FILE_PATH,
                DATABASE_USERNAME_FILENAME));
    }

    private void setLocalDatabasePasswordByFile() {
        databasePassword = CryptoWrapper.getPlainText(ciphers[1], FileTools.getCharArrayByFile(ENCRYPTED_FILE_PATH,
                DATABASE_PASSWORD_FILENAME));
    }

    public boolean setLocalAndFileDatabase(char[] databaseUrl, char[] databaseUsername, char[] databasePassword) {
        FileTools.setFileByCharArray(ENCRYPTED_FILE_PATH, DATABASE_URL_FILENAME, CryptoWrapper.getCipherText(ciphers[0], databaseUrl));
        FileTools.setFileByCharArray(ENCRYPTED_FILE_PATH, DATABASE_USERNAME_FILENAME, CryptoWrapper.getCipherText(ciphers[0],
                databaseUsername));
        FileTools.setFileByCharArray(ENCRYPTED_FILE_PATH, DATABASE_PASSWORD_FILENAME, CryptoWrapper.getCipherText(ciphers[0],
                databasePassword));
        return tryDatabaseConnection();
    }

    public boolean createDatabaseConnection() {
        if (databaseUrl != null && databaseUsername != null && databasePassword != null) {
            databaseConnector.setConnection(databaseUrl, databaseUsername, databasePassword);
            currentUser = databaseConnector.getUser(userUsername, userPasswordHash);
            if(currentUser == null) {
                if(databaseConnector.setUser(new User(userUsername, userPasswordHash, false, false,
                        false, true, new Date(System.currentTimeMillis()),
                        new Time(System.currentTimeMillis()), new Date(System.currentTimeMillis()),
                        new Time(System.currentTimeMillis())))) {
                    currentUser = databaseConnector.getUser(userUsername, userPasswordHash);
                }
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
        System.out.println("Error Message: " + message);
    }

    private Scene openPopup(String title, String fxml) {
        FXMLLoader loader;
        Parent tempRoot;
        Stage tempStage;
        try {
            loader = new FXMLLoader(getClass().getResource(fxml));
            tempRoot = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        tempStage = new Stage();
        tempStage.setTitle(title);
        tempStage.getIcons().add(Global.LOGO);
        tempStage.initModality(Modality.APPLICATION_MODAL);
        this.tempStage = tempStage;
        return new Scene(tempRoot, Global.DEFAULT_POPUP_WIDTH, Global.DEFAULT_POPUP_HEIGHT);
    }
}
