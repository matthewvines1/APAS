module com.example.socialmediaproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.opencsv;


    opens com.example.socialmediaproject to javafx.fxml;
    exports com.example.socialmediaproject;
}