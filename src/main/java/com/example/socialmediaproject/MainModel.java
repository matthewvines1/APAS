package com.example.socialmediaproject;

import com.example.socialmediaproject.databaseentities.User;

import javax.crypto.Cipher;

import java.util.Arrays;

import static javafx.application.Platform.exit;

public class MainModel {

    private final String DATABASE_URL = "jdbc:mysql://localhost:3306/mysql";
    private final String DATABASE_USERNAME = "root";
    private final String DATABASE_PASSWORD = "Multiplexor4:1MUX";
    private final String USER_USERNAME = "matthewvines0819@gmail.com";
    private final String USER_PASSWORD = "Multiplexor4:1MUX";

    private ImportExportModel importExportModel;
    private EditContactsModel editContactsModel;
    private DatabaseConnector databaseConnector;

    public MainModel() {
        importExportModel = new ImportExportModel();
        editContactsModel = new EditContactsModel();
        //databaseConnector =  new DatabaseConnector(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        //User currentUser = databaseConnector.getUser(USER_USERNAME, CryptoWrapper.generateHash(USER_USERNAME, USER_PASSWORD));
        //System.out.println(currentUser.getCreationDate());
        Cipher[] ciphers = CryptoWrapper.getCipher(USER_USERNAME);
        char[] testCryptoString = "It Works!".toCharArray();
        char[] cipherText = CryptoWrapper.getCipherText(ciphers[0], testCryptoString);
        char[] plainText = CryptoWrapper.getPlainText(ciphers[1], cipherText);
        System.out.println(String.valueOf(plainText));
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
}
