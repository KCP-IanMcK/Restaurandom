module com.example.restaurandomfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires org.json;


    opens com.example.restaurandomfx to javafx.fxml;
    exports com.example.restaurandomfx;
}