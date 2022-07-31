package com.example.socialmediaproject;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ImportExportContentController {
    Stage stage;
    ImportExportModel importExportModel;
    private int paneSelection1;
    private int paneSelection2;
    private int paneSelection3;
    private File file;

    @FXML
    VBox dropTarget;

    @FXML
    Label fileName;
    @FXML
    VBox uploadButtonContainer;

    @FXML
    public void initialize() {
        uploadButtonContainer.setVisible(false);
    }

    protected void setStageAndSelection(Stage stage, ImportExportModel importExportModel, int paneSelection1, int paneSelection2, int paneSelection3) {
        this.stage = stage;
        this.importExportModel = importExportModel;
        this.paneSelection1 = paneSelection1;
        this.paneSelection2 = paneSelection2;
        this.paneSelection3 = paneSelection3;
        if(paneSelection1 == 0 && paneSelection2 == 0) {
            String extensionString = "";
            switch(paneSelection3) {
                case 0:
                    extensionString = ".vcf";
                    break;
                case 1:
                case 2:
                    extensionString = ".csv";
                    break;
                case 3:
                    extensionString = ".zip";
                    break;
            }
            String finalExtensionString = extensionString;
            dropTarget.setOnDragOver(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent dragEvent) {
                    if(dragEvent.getGestureSource() != dropTarget && dragEvent.getDragboard().hasFiles()) {
                        String fileName = dragEvent.getDragboard().getFiles().get(0).getName();
                        if(fileName.length() >= finalExtensionString.length()) {
                            if (fileName.substring(fileName.length() - finalExtensionString.length()).equalsIgnoreCase(finalExtensionString)) {
                                dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                            }
                        }
                    }
                    dragEvent.consume();
                }
            });
            dropTarget.setOnDragDropped(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent dragEvent) {
                    Dragboard db = dragEvent.getDragboard();
                    boolean success = false;
                    if(db.hasFiles()) {
                        fileName.setText(db.getFiles().get(0).getName());
                        importExportModel.selectFile(db.getFiles().get(0));
                        uploadButtonContainer.setVisible(true);
                        success = true;
                    }
                    dragEvent.setDropCompleted(success);
                    dragEvent.consume();
                }
            });
        }
    }

    @FXML
    protected void browseFiles() {
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
        file = fileChooser.showOpenDialog(stage);
        if(file != null) {
            fileName.setText(file.getName());
            importExportModel.selectFile(file);
            uploadButtonContainer.setVisible(true);
        }
    }
    @FXML
    protected void upload() {
        importExportModel.upload();
    }
}
