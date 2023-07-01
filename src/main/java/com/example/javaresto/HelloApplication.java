package com.example.javaresto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import com.example.javaresto.classes.Dish;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("restaurant-creation-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("RestaurantJava");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        // add stock to the list for calculating expense
        // Money.setProductionPrice(/* dish arraylist here */); 

        // Display the menu
        Dish.displayMenu();
        
        launch();
    }
}