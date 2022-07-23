package com.example.socialmediaproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 960, 600);
        stage.setTitle("APAS!");

        //Menu Bar
        MenuBar menuBar = new MenuBar();
        //File
        Menu menu1 = new Menu("File");
        Menu menuItem1_1 = new Menu("Import");
        Menu menuItem1_1_1 = new Menu("External/Cloud");
        MenuItem menuItem1_1_1_1 = new MenuItem("Contacts");
        MenuItem menuItem1_1_1_2 = new MenuItem("Invoices");
        MenuItem menuItem1_1_1_3 = new MenuItem("All Files");
        menuItem1_1_1.getItems().addAll(menuItem1_1_1_1, menuItem1_1_1_2, menuItem1_1_1_3);
        Menu menuItem1_1_2 = new Menu("Internal");
        MenuItem menuItem1_1_2_1 = new MenuItem("Contacts vCard");
        MenuItem menuItem1_1_2_2 = new MenuItem("Contacts CSV");
        MenuItem menuItem1_1_2_3 = new MenuItem("Invoices CSV");
        MenuItem menuItem1_1_2_4 = new MenuItem("All Files ZIP");
        menuItem1_1_2.getItems().addAll(menuItem1_1_2_1, menuItem1_1_2_2, menuItem1_1_2_3, menuItem1_1_2_4);
        menuItem1_1.getItems().addAll(menuItem1_1_1, menuItem1_1_2);
        Menu menuItem1_2 = new Menu("Export");
        Menu menuItem1_2_1 = new Menu("External/Cloud");
        MenuItem menuItem1_2_1_1 = new MenuItem("Contacts");
        MenuItem menuItem1_2_1_2 = new MenuItem("Invoices");
        MenuItem menuItem1_2_1_3 = new MenuItem("All Files");
        menuItem1_2_1.getItems().addAll(menuItem1_2_1_1, menuItem1_2_1_2, menuItem1_2_1_3);
        Menu menuItem1_2_2 = new Menu("Internal");
        MenuItem menuItem1_2_2_1 = new MenuItem("Contacts vCard");
        MenuItem menuItem1_2_2_2 = new MenuItem("Contacts CSV");
        MenuItem menuItem1_2_2_3 = new MenuItem("Invoices CSV");
        MenuItem menuItem1_2_2_4 = new MenuItem("All Files ZIP");
        menuItem1_2_2.getItems().addAll(menuItem1_2_2_1, menuItem1_2_2_2, menuItem1_2_2_3, menuItem1_2_2_4);
        menuItem1_2.getItems().addAll(menuItem1_2_1, menuItem1_2_2);
        MenuItem menuItem1_3 = new MenuItem("Placeholder 3");
        menu1.getItems().addAll(menuItem1_1, menuItem1_2, menuItem1_3);
        //Edit
        Menu menu2 = new Menu("Edit");
        MenuItem menuItem2_1 = new MenuItem("Edit Contacts");
        MenuItem menuItem2_2 = new MenuItem("Placeholder 2");
        MenuItem menuItem2_3 = new MenuItem("Placeholder 3");
        menu2.getItems().addAll(menuItem2_1, menuItem2_2, menuItem2_3);
        //View
        Menu menu3 = new Menu("View");
        MenuItem menuItem3_1 = new MenuItem("Placeholder 1");
        MenuItem menuItem3_2 = new MenuItem("Placeholder 2");
        MenuItem menuItem3_3 = new MenuItem("Placeholder 3");
        menu3.getItems().addAll(menuItem3_1, menuItem3_2, menuItem3_3);
        //Help
        Menu menu4 = new Menu("Help");
        MenuItem menuItem4_1 = new MenuItem("Placeholder 1");
        MenuItem menuItem4_2 = new MenuItem("Placeholder 2");
        MenuItem menuItem4_3 = new MenuItem("About");
        menu4.getItems().addAll(menuItem4_1, menuItem4_2, menuItem4_3);
        menuBar.getMenus().addAll(menu1, menu2, menu3, menu4);

        VBox vBox = new VBox(menuBar);
        Scene scene = new Scene(vBox, 900, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}