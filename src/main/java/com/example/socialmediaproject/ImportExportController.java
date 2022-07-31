package com.example.socialmediaproject;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ImportExportController {

    private final String TITLE_IMPORT_INTERNAL_VCARD = "Import Contacts vCard";
    private final String TITLE_IMPORT_INTERNAL_CSV = "Import Contacts CSV";
    private final String TITLE_IMPORT_INTERNAL_INVOICES = "Import Invoices CSV";
    private final String TITLE_IMPORT_INTERNAL_ALL = "Import All Files ZIP";
    private final String TITLE_IMPORT_EXTERNAL_CONTACTS = "Import Contacts";
    private final String TITLE_IMPORT_EXTERNAL_INVOICES = "Import Invoices";
    private final String TITLE_IMPORT_EXTERNAL_ALL = "Import All";
    private final String TITLE_EXPORT_INTERNAL_VCARD = "Export Contacts vCard";
    private final String TITLE_EXPORT_INTERNAL_CSV = "Export Contacts CSV";
    private final String TITLE_EXPORT_INTERNAL_INVOICES = "Export Invoices CSV";
    private final String TITLE_EXPORT_INTERNAL_ALL = "Export All Files ZIP";
    private final String TITLE_EXPORT_EXTERNAL_CONTACTS = "Export Contacts";
    private final String TITLE_EXPORT_EXTERNAL_INVOICES = "Export Invoices";
    private final String TITLE_EXPORT_EXTERNAL_ALL = "Export All";
    private ImportExportModel importExportModel;
    private Parent importExportContentRoot;

    @FXML
    TabPane mainTabPane;
    @FXML
    TabPane importTabPane;
    @FXML
    TabPane importInternalTabPane;
    @FXML
    TabPane importExternalTabPane;
    @FXML
    TabPane exportTabPane;
    @FXML
    TabPane exportInternalTabPane;
    @FXML
    TabPane exportExternalTabPane;

    private int paneSelection1;
    private int paneSelection2;
    private int paneSelection3;
    public ImportExportController() {
        importExportModel = new ImportExportModel();
    }

    public void menuChange() {
        ImportExportContentController importExportContentController;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("import-export-content.fxml"));
            importExportContentRoot = fxmlLoader.load();
            importExportContentController = fxmlLoader.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (mainTabPane != null) {
            paneSelection1 = mainTabPane.getSelectionModel().getSelectedIndex();
            Label titleLabel = (Label) importExportContentRoot.lookup("#importExportLabel");
            switch (paneSelection1) {
                case 0:
                    paneSelection2 = importTabPane.getSelectionModel().getSelectedIndex();
                    switch (paneSelection2) {
                        case 0:
                            paneSelection3 = importInternalTabPane.getSelectionModel().getSelectedIndex();
                            switch (paneSelection3) {
                                case 0:
                                    titleLabel.setText(TITLE_IMPORT_INTERNAL_VCARD);
                                    break;
                                case 1:
                                    titleLabel.setText(TITLE_IMPORT_INTERNAL_CSV);
                                    break;
                                case 2:
                                    titleLabel.setText(TITLE_IMPORT_INTERNAL_INVOICES);
                                    break;
                                case 3:
                                    titleLabel.setText(TITLE_IMPORT_INTERNAL_ALL);
                                    break;
                            }
                            importInternalTabPane.getTabs().get(paneSelection3).setContent(importExportContentRoot);
                            break;
                        case 1:
                            paneSelection3 = importExternalTabPane.getSelectionModel().getSelectedIndex();
                            switch (paneSelection3) {
                                case 0:
                                    titleLabel.setText(TITLE_IMPORT_EXTERNAL_CONTACTS);
                                    break;
                                case 1:
                                    titleLabel.setText(TITLE_IMPORT_EXTERNAL_INVOICES);
                                    break;
                                case 2:
                                    titleLabel.setText(TITLE_IMPORT_EXTERNAL_ALL);
                                    break;
                            }
                            importExternalTabPane.getTabs().get(paneSelection3).setContent(importExportContentRoot);
                            break;
                    }
                    break;
                case 1:
                    paneSelection2 = exportTabPane.getSelectionModel().getSelectedIndex();
                    switch (paneSelection2) {
                        case 0:
                            paneSelection3 = exportInternalTabPane.getSelectionModel().getSelectedIndex();
                            switch (paneSelection3) {
                                case 0:
                                    titleLabel.setText(TITLE_EXPORT_INTERNAL_VCARD);
                                    break;
                                case 1:
                                    titleLabel.setText(TITLE_EXPORT_INTERNAL_CSV);
                                    break;
                                case 2:
                                    titleLabel.setText(TITLE_EXPORT_INTERNAL_INVOICES);
                                    break;
                                case 3:
                                    titleLabel.setText(TITLE_EXPORT_INTERNAL_ALL);
                                    break;
                            }
                            exportInternalTabPane.getTabs().get(paneSelection3).setContent(importExportContentRoot);
                            break;
                        case 1:
                            paneSelection3 = exportExternalTabPane.getSelectionModel().getSelectedIndex();
                            switch (paneSelection3) {
                                case 0:
                                    titleLabel.setText(TITLE_EXPORT_EXTERNAL_CONTACTS);
                                    break;
                                case 1:
                                    titleLabel.setText(TITLE_EXPORT_EXTERNAL_INVOICES);
                                    break;
                                case 2:
                                    titleLabel.setText(TITLE_EXPORT_EXTERNAL_ALL);
                                    break;
                            }
                            exportExternalTabPane.getTabs().get(paneSelection3).setContent(importExportContentRoot);
                            break;
                    }
                    break;
            }
        }
        importExportContentController.setStageAndSelection((Stage)mainTabPane.getScene().getWindow(), paneSelection1, paneSelection2, paneSelection3);
    }

    @FXML
    public void initialize() {
        mainTabPane.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Tab>() {
                    @Override
                    public void changed(ObservableValue<? extends Tab> observableValue, Tab tab, Tab t1) {
                        menuChange();
                    }
                }
        );
        importTabPane.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Tab>() {
                    @Override
                    public void changed(ObservableValue<? extends Tab> observableValue, Tab tab, Tab t1) {
                        menuChange();
                    }
                }
        );
        importInternalTabPane.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Tab>() {
                    @Override
                    public void changed(ObservableValue<? extends Tab> observableValue, Tab tab, Tab t1) {
                        menuChange();
                    }
                }
        );
        importExternalTabPane.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Tab>() {
                    @Override
                    public void changed(ObservableValue<? extends Tab> observableValue, Tab tab, Tab t1) {
                        menuChange();
                    }
                }
        );
        exportTabPane.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Tab>() {
                    @Override
                    public void changed(ObservableValue<? extends Tab> observableValue, Tab tab, Tab t1) {
                        menuChange();
                    }
                }
        );
        exportInternalTabPane.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Tab>() {
                    @Override
                    public void changed(ObservableValue<? extends Tab> observableValue, Tab tab, Tab t1) {
                        menuChange();
                    }
                }
        );
        exportExternalTabPane.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Tab>() {
                    @Override
                    public void changed(ObservableValue<? extends Tab> observableValue, Tab tab, Tab t1) {
                        menuChange();
                    }
                }
        );
    }

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
