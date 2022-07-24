package com.example.socialmediaproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class ImportExportController {

    private ImportExportModel importExportModel = new ImportExportModel();

    @FXML
    protected void importContactsVCard(javafx.event.ActionEvent event) throws IOException {

    }

    @FXML
    protected void importContactsCSV() {
        importExportModel.importContactsCSV();
    }

    @FXML
    protected void importInvoicesCSV() {
        importExportModel.importInvoicesCSV();
    }

    @FXML
    protected void importAllFilesZIP () {
        importExportModel.importAllFilesZIP();
    }

    @FXML
    protected void importContactsCloud () {
        importExportModel.importContactsCloud();
    }

    @FXML
    protected void importInvoicesCloud () {
        importExportModel.importInvoicesCloud();
    }

    @FXML
    protected void importAllFilesCloud () {
        importExportModel.importAllFilesCloud();
    }

    @FXML
    protected void exportContactsVCard() {
        importExportModel.exportContactsVCard();
    }

    @FXML
    protected void exportContactsCSV() {
        importExportModel.exportContactsCSV();
    }

    @FXML
    protected void exportInvoicesCSV() {
        importExportModel.exportInvoicesCSV();
    }

    @FXML
    protected void exportAllFilesZIP () {
        importExportModel.exportAllFilesZIP();
    }

    @FXML
    protected void exportContactsCloud () {
        importExportModel.exportContactsCloud();
    }

    @FXML
    protected void exportInvoicesCloud () {
        importExportModel.exportInvoicesCloud();
    }

    @FXML
    protected void exportAllFilesCloud () {
        importExportModel.exportAllFilesCloud();
    }
}
