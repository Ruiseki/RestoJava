package com.example.javaresto;

import com.example.javaresto.classes.Restaurant;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import com.example.javaresto.classes.Chrono;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("order-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("RestoJava");
        stage.setScene(scene);
        stage.show();
    }
    public void GenerateRestaurant() {
         Restaurant resto = new Restaurant("RestoJava", "42 Rue de la Java", "Le meilleur restaurant de la JavaVille");
    }
    public static void main(String[] args) {
        launch();
        Chrono chrono = new Chrono();
        chrono.start(0, false);
    }
}