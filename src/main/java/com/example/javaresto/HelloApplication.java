package com.example.javaresto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import com.example.javaresto.classes.Chrono;
import com.example.javaresto.classes.Dish;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("order-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("RestoJava");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        /*Image pizzaImage = new Image("/Users/smajicmathias/Desktop/JavaResto/src/main/resources/com/example/javaresto/image/pizzaMargherita.jpg");
        Image saladImage = new Image("/Users/smajicmathias/Desktop/JavaResto/src/main/resources/com/example/javaresto/image/saladCesar.jpg");
        Image pastaImage = new Image("/Users/smajicmathias/Desktop/JavaResto/src/main/resources/com/example/javaresto/image/pastaBolo.jpg");*/

        Dish.addDish(Dish.createDish("Spaghetti bolognese", "Pasta with bolognese sauce", 8.50, 10.00, null));
        Dish.addDish(Dish.createDish("Pizza margherita", "Tomate, mozzarella, basilic", 9.00, 11.00, null));
        Dish.addDish(Dish.createDish("Caesar salad", "Green salad, chicken, parmesan, croutons", 7.50, 9.00, null));

        // Afficher la liste de plats
        Dish.displayMenu();

        // Afficher les détails d'un plat spécifique
        Dish.displayDishDetails("Caesar salad");
        
        launch();
        Chrono chrono = new Chrono();
        chrono.start(0, false);
    }
}