package com.example.javaresto;

import com.example.javaresto.classes.Restaurant;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import com.example.javaresto.classes.Chrono;
import com.example.javaresto.classes.Dish;
import com.example.javaresto.classes.Money;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("restaurant-creation-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("RestoJava");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Chrono chrono = new Chrono();

        // use this to sync the chrono
        // chrono.startSync(15000, false);
        
        // use this one to allow background operation
        chrono.startThreaded(15000, false);

        // add stock to the list for calculating expense
        // Money.setProductionPrice(/* dish arraylist here */); 

        // Afficher la liste de plats
        Dish.displayMenu();

        // Afficher les détails d'un plat spécifique
        Dish.displayDishDetails("Caesar salad");
        
        launch();
        //Chrono chrono = new Chrono();
        //chrono.start(0, false);
    }
}