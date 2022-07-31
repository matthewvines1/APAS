package com.example.socialmediaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ImportExportContentController {
    Stage stage;
    private int paneSelection1;
    private int paneSelection2;
    private int paneSelection3;
    private boolean isFileChooserOpen;

    @FXML
    VBox dropTarget;

    public void setStageAndSelection(Stage stage, int paneSelection1, int paneSelection2, int paneSelection3) {
        this.stage = stage;
        this.paneSelection1 = paneSelection1;
        this.paneSelection2 = paneSelection2;
        this.paneSelection3 = paneSelection3;
        isFileChooserOpen = false;
    }

    public boolean getIsFileChooserOpen() {
        return isFileChooserOpen;
    }

    @FXML
    protected void browseFiles() {
        isFileChooserOpen = true;
        FileChooser fileChooser = new FileChooser();
        String title = "Select File";
        String fileExtensionFilter = "";
        String fileExtensionFilterDescription = "";
        switch(paneSelection1) {
            case 0:
                switch(paneSelection2) {
                    case 0:
                        switch(paneSelection3) {
                            case 0:
                                title = "Select Contact vCard";
                                fileExtensionFilter = "*.vcf";
                                fileExtensionFilterDescription = "vCard Files (*.vcf)";
                                break;
                            case 1:
                                title = "Select Contact CSV";
                                fileExtensionFilter = "*.csv";
                                fileExtensionFilterDescription = "CSV Files (*.csv)";
                                break;
                            case 2:
                                title = "Select Invoices CSV";
                                fileExtensionFilter = "*.csv";
                                fileExtensionFilterDescription = "CSV Files (*.csv)";
                                break;
                            case 3:
                                title = "Select All Files ZIP";
                                fileExtensionFilter = "*.zip";
                                fileExtensionFilterDescription = "ZIP Files (*.zip)";
                                break;
                        }
                        break;
                }
                break;
        }
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(fileExtensionFilterDescription, fileExtensionFilter));
        fileChooser.setTitle(title);
        Stage tempStage = new Stage();
        tempStage.setAlwaysOnTop(true);
        tempStage.initModality(Modality.APPLICATION_MODAL);
        tempStage.initOwner(stage);
        fileChooser.showOpenDialog(tempStage);
    }
}
