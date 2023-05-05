package com.example.javaresto.controller;

import com.example.javaresto.classes.Dish;
import com.example.javaresto.classes.Ingredient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class IngredientController implements Initializable {

        @FXML
        private Button add;

        @FXML
        private TableColumn<Ingredient, String> name;

        @FXML
        private TableColumn<?, ?> price;

        @FXML
        private Button reset;

        @FXML
        private TableView<Ingredient> table;

        @FXML
        private ComboBox<String> ingredientBox;

        private List<Ingredient> ingredientList = new ArrayList<Ingredient>();

    public  Ingredient createIngredient(String name, double price) {
        Ingredient ingredient = new Ingredient(name, price);
        return ingredient;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ingredientList.stream().forEach(ingredient -> ingredientBox.getItems().add(ingredient.getName()));

        Ingredient.addIngredient(Ingredient.createIngredient("Pasta", 2.50), ingredientList);
        Ingredient.addIngredient(Ingredient.createIngredient("Sauce bolognese", 5.00), ingredientList);
        Ingredient.addIngredient(Ingredient.createIngredient("Tomato", 1.00), ingredientList);
        Ingredient.addIngredient(Ingredient.createIngredient("Mozzarella", 1.00), ingredientList);
        Ingredient.addIngredient(Ingredient.createIngredient("Dough", 8.00), ingredientList);
        Ingredient.addIngredient(Ingredient.createIngredient("Basilic", 1.00), ingredientList);
        Ingredient.addIngredient(Ingredient.createIngredient("Green Salad", 1.00), ingredientList);
        Ingredient.addIngredient(Ingredient.createIngredient("Chicken", 3.00), ingredientList);
        Ingredient.addIngredient(Ingredient.createIngredient("Parmesan", 1.00), ingredientList);
        Ingredient.addIngredient(Ingredient.createIngredient("Crouton", 2.00), ingredientList);

        Dish.addDish(Dish.createDish("Spaghetti bolognese", "Pasta with bolognese sauce", Dish.calculateNetPrice(8.50), 8.50, null));
        Dish.addDish(Dish.createDish("Pizza margherita", "Tomate, mozzarella, basilic", Dish.calculateNetPrice(10.00), 10.00, null));
        Dish.addDish(Dish.createDish("Caesar salad", "Green salad, chicken, parmesan, croutons", Dish.calculateNetPrice(6.00), 6.0, null));

        // Display the list of dishes
        Dish.displayMenu();

        // Display the details of a "dish"
        Dish.displayDishDetails("Caesar salad");

        Ingredient.displayIngredient(ingredientList);

    }
}
