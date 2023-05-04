package com.example.javaresto;

import com.example.javaresto.classes.Dish;
import com.example.javaresto.classes.Ingredient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ingredient-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        //Image pizzaImage = new Image("/JavaResto/src/main/resources/com/example/javaresto/image/pizzaMargherita.jpg");
        //Image saladImage = new Image("/JavaResto/src/main/resources/com/example/javaresto/image/saladCesar.jpg");
        //Image pastaImage = new Image("src/main/resources/com/example/javaresto/image/pastaBolo.jpg");

        Ingredient.addIngredient(Ingredient.createIngredient("Pasta", 2.50));
        Ingredient.addIngredient(Ingredient.createIngredient("Sauce bolognese", 5.00));
        Ingredient.addIngredient(Ingredient.createIngredient("Tomato", 1.00));
        Ingredient.addIngredient(Ingredient.createIngredient("Mozzarella", 1.00));
        Ingredient.addIngredient(Ingredient.createIngredient("Dough", 8.00));
        Ingredient.addIngredient(Ingredient.createIngredient("Basilic", 1.00));
        Ingredient.addIngredient(Ingredient.createIngredient("Green Salad", 1.00));
        Ingredient.addIngredient(Ingredient.createIngredient("Chicken", 3.00));
        Ingredient.addIngredient(Ingredient.createIngredient("Parmesan", 1.00));
        Ingredient.addIngredient(Ingredient.createIngredient("Crouton", 2.00));


        Dish.addDish(Dish.createDish("Spaghetti bolognese", "Pasta with bolognese sauce", Dish.calculateNetPrice(8.50), 8.50, null));
        Dish.addDish(Dish.createDish("Pizza margherita", "Tomate, mozzarella, basilic", Dish.calculateNetPrice(10.00), 10.00, null));
        Dish.addDish(Dish.createDish("Caesar salad", "Green salad, chicken, parmesan, croutons", Dish.calculateNetPrice(6.00), 6.0, null));

        // Display the list of dishes
        Dish.displayMenu();

        // Display the details of a "dish"
        Dish.displayDishDetails("Caesar salad");

        Ingredient.displayIngredient();

        launch();
    }

}