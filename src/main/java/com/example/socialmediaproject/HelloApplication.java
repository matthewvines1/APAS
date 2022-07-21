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
        stage.setTitle("Hello!");

        //Menu Bar
        MenuBar menuBar = new MenuBar();
        //File
        Menu menu1 = new Menu("File");
        MenuItem menuItem1_1 = new MenuItem("Placeholder 1");
        menu1.getItems().add(menuItem1_1);
        MenuItem menuItem1_2 = new MenuItem("Placeholder 2");
        menu1.getItems().add(menuItem1_2);
        MenuItem menuItem1_3 = new MenuItem("Placeholder 3");
        menu1.getItems().add(menuItem1_3);
        menuBar.getMenus().add(menu1);
        //Edit
        Menu menu2 = new Menu("Edit");
        MenuItem menuItem2_1 = new MenuItem("Placeholder 1");
        menu2.getItems().add(menuItem2_1);
        MenuItem menuItem2_2 = new MenuItem("Placeholder 2");
        menu2.getItems().add(menuItem2_2);
        MenuItem menuItem2_3 = new MenuItem("Placeholder 3");
        menu2.getItems().add(menuItem2_3);
        menuBar.getMenus().add(menu2);
        //View
        Menu menu3 = new Menu("View");
        MenuItem menuItem3_1 = new MenuItem("Placeholder 1");
        menu3.getItems().add(menuItem3_1);
        MenuItem menuItem3_2 = new MenuItem("Placeholder 2");
        menu3.getItems().add(menuItem3_2);
        MenuItem menuItem3_3 = new MenuItem("Placeholder 3");
        menu3.getItems().add(menuItem3_3);
        menuBar.getMenus().add(menu3);
        //Help
        Menu menu4 = new Menu("Help");
        MenuItem menuItem4_1 = new MenuItem("Placeholder 1");
        menu4.getItems().add(menuItem4_1);
        MenuItem menuItem4_2 = new MenuItem("Placeholder 2");
        menu4.getItems().add(menuItem4_2);
        MenuItem menuItem4_3 = new MenuItem("About");
        menu4.getItems().add(menuItem4_3);
        menuBar.getMenus().add(menu4);

        VBox vBox = new VBox(menuBar);
        Scene scene = new Scene(vBox, 960, 900);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}