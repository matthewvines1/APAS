package com.example.socialmediaproject;

import com.example.socialmediaproject.databaseentities.Contact;

import java.io.File;

public class ImportExportModel {

    private int selection1;
    private int selection2;
    private int selection3;
    private File file;
    private DatabaseConnector databaseConnector;

    protected void setSelection(int selection1, int selection2, int selection3) {
        this.selection1 = selection1;
        this.selection2 = selection2;
        this.selection3 = selection3;
    }
    protected void selectFile(File file) {
        this.file = file;
    }

    protected boolean upload() {
        switch(selection1) {
            case 0:
                switch(selection2) {
                    case 0:
                        switch(selection3) {
                            case 0:
                                return importContactsCSV();
                            case 1:
                                return importInvoicesCSV();
                            case 2:
                                return importAllFilesZIP();
                            case 3:
                                return importContactsVCard();
                        }
                        break;
                }
                break;
        }
        return false;
    }

    public void setDatabaseConnector(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    protected boolean importContactsVCard() {
        return false;
    }

    protected boolean importContactsCSV() {
        //Parse csv and upload
        return false;
    }

    protected boolean importInvoicesCSV() {
        //Parse csv and upload
        return false;
    }

    protected boolean importAllFilesZIP() {
        return false;
    }

    protected boolean importContactsCloud() {
        return false;
    }

    protected boolean importInvoicesCloud () {
        return false;
    }

    protected boolean importAllFilesCloud () {
        return false;
    }

    protected boolean exportContactsVCard() {
        return false;
    }

    protected boolean exportContactsCSV() {
        return false;
    }

    protected boolean exportInvoicesCSV() {
        return false;
    }

    protected boolean exportAllFilesZIP () {
        return false;
    }

    protected boolean exportContactsCloud () {
        return false;
    }

    protected boolean exportInvoicesCloud () {
        return false;
    }

    protected boolean exportAllFilesCloud () {
        return false;
    }

    private void log(String functionName, String message) {
        Global.log("ImportExportModel", functionName, message);
    }
}
