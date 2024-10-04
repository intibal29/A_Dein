module org.example.a {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.a to javafx.fxml;
    exports org.example.a;
}