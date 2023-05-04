package com.example.javaresto.controller;

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

        public void addIngredient(Ingredient ingredient) {
            ingredientList.add((Ingredient) ingredient);
        }

        public void displayIngredient() {
            System.out.println("Ingredient :");
            ingredientList.stream()
                    .map(dish -> "- " + dish.getName() + " (" + dish.getPrice() + "$)")
                    // Removing duplicates
                    .distinct()
                    .forEach(System.out::println);
        }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ingredientList.stream().forEach(ingredient -> ingredientBox.getItems().add(ingredient.getName()));
    }
}
