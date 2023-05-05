package com.example.javaresto.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class IngredientController {

        @FXML
        private Button add;

        @FXML
        private TableColumn<?, ?> name;

        @FXML
        private TableColumn<?, ?> price;

        @FXML
        private Button reset;

        @FXML
        private TableView<?> table;

        @FXML
        private void handleAddButtonAction(ActionEvent event){

        }
}
