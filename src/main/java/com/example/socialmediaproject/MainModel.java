package com.example.socialmediaproject;

import static javafx.application.Platform.exit;

public class MainModel {

    private ImportExportModel importExportModel;
    private EditContactsModel editContactsModel;

    public void MainModel() {
        importExportModel = new ImportExportModel();
        editContactsModel = new EditContactsModel();
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
