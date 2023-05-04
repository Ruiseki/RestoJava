package com.example.javaresto;

import com.example.javaresto.classes.Restaurant;
import com.example.javaresto.classes.Room;
import com.example.javaresto.classes.Table;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public class MainPageController {

    @FXML
    private ComboBox<String> MyrestaurantRoomsComboBox;

    @FXML
    private TextField TablesPlacesNumberTextfield;

    @FXML
    private TextField createRAddressTextfield;

    @FXML
    private TextField createRDescriptionTextfield;

    @FXML
    private TextField createRNameTextfield;

    @FXML
    private Button creationTableButton;

    @FXML
    private Label displayEmptyTablesLabel1;

    @FXML
    private Label displayEmptyTablesLabel2;

    @FXML
    private Label displayRAddressLabel;

    @FXML
    private Label displayRDescriptionLabel;

    @FXML
    private Label displayRNameLabel;

    @FXML
    private Label displayRoomNameLabel1;

    @FXML
    private Label displayRoomNameLabel2;

    @FXML
    private Label displayRoomNumTablesLabel1;

    @FXML
    private Label displayRoomNumTablesLabel2;

    public Restaurant Myrestaurant;
    @FXML
    void btnCreateClicked(ActionEvent event) {
        String name = createRNameTextfield.getText();
        String description = createRDescriptionTextfield.getText();
        String address = createRAddressTextfield.getText();

        ArrayList<Table> tables = new ArrayList<Table>();
        Table table1 = new Table(1, 4, 2);
        tables.add(table1);
        Table table2 = new Table(2, 4, 1);
        tables.add(table2);
        Table table3 = new Table(3, 6, 1);
        tables.add(table3);
        Table table4 = new Table(4, 4, 1);
        tables.add(table4);
        Table table5 = new Table(5, 4, 1);
        tables.add(table5);
        Table table6 = new Table(6, 8, 2);
        tables.add(table6);

        Myrestaurant = new Restaurant(name, address, description);
        Myrestaurant.getRooms().get(0).setTables(tables);
        List restaurantRoomStream = Myrestaurant.getRooms().stream().map(Room::getName).toList();
        MyrestaurantRoomsComboBox.getItems().clear();
        MyrestaurantRoomsComboBox.getItems().addAll(restaurantRoomStream);
    }

    @FXML
    void btnDisplayClicked(ActionEvent event) {
        displayRNameLabel.setText(Myrestaurant.getName());
        displayRDescriptionLabel.setText(Myrestaurant.getDescription());
        displayRAddressLabel.setText(Myrestaurant.getAddress());


        Myrestaurant.getRooms().stream().forEach(
                (room) -> {
                    int roomTablesQuantityAvalaible =  room.getNumberOfTableAvailable();
                    if (room.getName().equals("Terrace")) {
                        displayRoomNameLabel1.setText(room.getName());
                        displayRoomNumTablesLabel1.setText(String.valueOf(roomTablesQuantityAvalaible));
                    } else {
                        displayRoomNameLabel2.setText(room.getName());
                        displayRoomNumTablesLabel2.setText(String.valueOf(roomTablesQuantityAvalaible));
                    }
                    // For each rooms display the number of Tables avalaible or not
                    if (room.getName().equals("Terrace")) {
                        int quantityOfTable = room.getTables().size();
                        displayEmptyTablesLabel1.setText("" + quantityOfTable);
                    } else {
                        int quantityOfTable = room.getTables().size();
                        displayEmptyTablesLabel2.setText("" + quantityOfTable);
                    }
                }
        );
    }
    @FXML
    void btnTableCreateClicked(ActionEvent event) {
        int NumberPlacesNewTable = Integer.parseInt(TablesPlacesNumberTextfield.getText());
        int locationRoomNewTable = MyrestaurantRoomsComboBox.getSelectionModel().getSelectedIndex();
        Room cibledroom = Myrestaurant.getRooms().get(locationRoomNewTable);
        int idNewTable = cibledroom.getTables().size() + 1;
        Table newTable = new Table(idNewTable, NumberPlacesNewTable, locationRoomNewTable);
        cibledroom.getTables().add(newTable);
    }
}
