package com.example.socialmediaproject;

import java.io.File;

public class ImportExportModel {

    private int selection1;
    private int selection2;
    private int selection3;
    private File file;

    protected void setSelection(int selection1, int selection2, int selection3) {
        this.selection1 = selection1;
        this.selection2 = selection2;
        this.selection3 = selection3;
    }
    protected void selectFile(File file) {
        this.file = file;
    }

    protected boolean upload() {
        return true;
    }

    protected boolean importContactsCSV() {
        //System.out.println(file.getName());
        return false;
    }

    protected boolean importInvoicesCSV() {
        return false;
    }

    protected boolean importAllFilesZIP () {
        return false;
    }

    protected boolean importContactsCloud () {
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
}
