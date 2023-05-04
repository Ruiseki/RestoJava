package com.example.javaresto;

import com.example.javaresto.classes.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainPageController {

    @FXML
    private TextField createRAddressTextfield;

    @FXML
    private TextField createRDescriptionTextfield;

    @FXML
    private TextField createRNameTextfield;

    @FXML
    private Label displayRAddressLabel;

    @FXML
    private Label displayRDescriptionLabel;

    @FXML
    private Label displayRNameLabel;
    public Restaurant Myrestaurant;
    @FXML
    void btnCreateClicked(ActionEvent event) {
        String name = createRNameTextfield.getText();
        String description = createRDescriptionTextfield.getText();
        String address = createRAddressTextfield.getText();
        Myrestaurant = new Restaurant(name, address, description);
    }

    @FXML
    void btnDisplayClicked(ActionEvent event) {
        displayRNameLabel.setText(Myrestaurant.getName());
        displayRDescriptionLabel.setText(Myrestaurant.getDescription());
        displayRAddressLabel.setText(Myrestaurant.getAddress());
    }

}
