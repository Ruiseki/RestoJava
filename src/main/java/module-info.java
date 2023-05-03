module com.example.javaresto {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javaresto to javafx.fxml;
    exports com.example.javaresto;
}