module com.example.socialmediaproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.socialmediaproject to javafx.fxml;
    exports com.example.socialmediaproject;
}