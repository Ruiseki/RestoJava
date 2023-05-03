module com.example.javaresto {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javaresto to javafx.fxml;
    exports com.example.javaresto;
    exports com.example.javaresto.classes;
    opens com.example.javaresto.classes to javafx.fxml;
}