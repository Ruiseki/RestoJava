package com.example.javaresto;

import com.example.javaresto.classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainPageController implements Initializable{

    @FXML
    private ComboBox<String> MyrestaurantRoomsComboBox;

    @FXML
    private ComboBox<String> MyrestaurantRoom1ComboBox;

    @FXML
    private ComboBox<String> MyrestaurantRoom2ComboBox;

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

    @FXML
    private ListView listViewOrder;

    @FXML
    private ComboBox<String> comboBoxDish;

    @FXML
    private Button addDishButton;

    @FXML
    private Button createOrderButton;

    @FXML
    private TextField textfieldName;

    @FXML
    private TextField nameDishTextField;

    @FXML
    private TextField priceDishTextField;

    @FXML
    private TextField imageDishTextField;

    @FXML
    private Label descriptionDishMenuLabel;

    @FXML
    private ComboBox<String> comboBoxDishMenu;


    public ArrayList<Dish> addedDishList = new ArrayList<>();
    public ArrayList<Dish> list = new ArrayList<>();
    public ArrayList<Ingredient> ingredientList = new ArrayList<>();
    public ArrayList<Order> listOrder = new ArrayList<>();

    public Restaurant Myrestaurant;
    @FXML
    void btnCreateClicked(ActionEvent event) {
        String name = createRNameTextfield.getText();
        String description = createRDescriptionTextfield.getText();
        String address = createRAddressTextfield.getText();

        ArrayList<Table> tables = new ArrayList<Table>();
        Table table1 = new Table(1, 4, 0);
        tables.add(table1);
        Table table2 = new Table(2, 4, 1);
        tables.add(table2);
        Table table3 = new Table(3, 6, 1);
        tables.add(table3);
        Table table4 = new Table(4, 4, 1);
        tables.add(table4);
        Table table5 = new Table(5, 4, 1);
        tables.add(table5);
        Table table6 = new Table(6, 8, 0);
        tables.add(table6);
        Table table7 = new Table(7, 4, 1);
        tables.add(table7);
        Table table8 = new Table(8, 4, 0);
        tables.add(table8);

        ArrayList<Table> listTableRoom1 = new ArrayList<>();

        ArrayList<Table> listTableRoom2 = new ArrayList<>();

        tables.stream().forEach( table ->
                {
                    if(table.getIdRoom() == 0){
                        listTableRoom1.add(table);
                    }
                    else {
                        listTableRoom2.add(table);
                    }
                }
            );

        Myrestaurant = new Restaurant(name, address, description);
        Myrestaurant.getRooms().get(0).setTables(listTableRoom1);
        Myrestaurant.getRooms().get(1).setTables(listTableRoom2);
        List restaurantRoomStream = Myrestaurant.getRooms().stream().map(Room::getName).toList();
        MyrestaurantRoomsComboBox.getItems().clear();
        MyrestaurantRoomsComboBox.getItems().addAll(restaurantRoomStream);
    }

    @FXML
    void btnDisplayClicked(ActionEvent event) {
        displayRNameLabel.setText(Myrestaurant.getName());
        displayRDescriptionLabel.setText(Myrestaurant.getDescription());
        displayRAddressLabel.setText(Myrestaurant.getAddress());

        // set the name of the room to there labels
        displayRoomNameLabel1.setText(Myrestaurant.getRooms().get(0).getName());
        displayRoomNameLabel2.setText(Myrestaurant.getRooms().get(1).getName());

        // set the number of tables to there labels
        displayRoomNumTablesLabel1.setText(String.valueOf(Myrestaurant.getRooms().get(0).getTables().size()));
        displayRoomNumTablesLabel2.setText(String.valueOf(Myrestaurant.getRooms().get(1).getTables().size()));

        // set the number of empty tables to there labels
        displayEmptyTablesLabel1.setText(String.valueOf(Myrestaurant.getRooms().get(0).getNumberOfTableAvailable()));
        displayEmptyTablesLabel2.setText(String.valueOf(Myrestaurant.getRooms().get(1).getNumberOfTableAvailable()));

        // set the empty tables to the ComboxBox
        MyrestaurantRoom1ComboBox.getItems().clear();
        MyrestaurantRoom1ComboBox.getItems().addAll(Myrestaurant.getRooms().get(0).getTables().stream().filter(table -> table.getIsAvailable()).map(Table::getIdTable).map(String::valueOf).toList());

        MyrestaurantRoom2ComboBox.getItems().clear();
        MyrestaurantRoom2ComboBox.getItems().addAll(Myrestaurant.getRooms().get(1).getTables().stream().filter(table -> table.getIsAvailable()).map(Table::getIdTable).map(String::valueOf).toList());


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

    /** ----------------------------------------------------------------------------------------------------------------
     * Order part
     */

    /**
     * Delete the order in the listOrder
     * @param order
     * @param listOrder
     */

    public void deleteOrder(Order order, ArrayList<Order> listOrder) {
        List<Order> savedListOrder = listOrder.stream().filter(currentOrder -> currentOrder != order).collect(Collectors.toList());
    }

    /**
     * Create the order calculating the net price and the raw price
     */

    public void createOrder() {
        Double totalNetPrice = addedDishList.stream().reduce(0.0, (result, dish) -> result + dish.getNetPrice(), Double::sum);
        Double totalRawPrice = addedDishList.stream().reduce(0.0, (result, dish) -> result + dish.getGrossPrice(), Double::sum);
        Order order = new Order(addedDishList, textfieldName.getText(), "pending", totalNetPrice, totalRawPrice);
        listOrder.add(order);
        System.out.println(listOrder.get(0).getDishes() + " " + listOrder.get(0).getStatus() + " " + listOrder.get(0).getCustomer() + " " + listOrder.get(0).getNetPrice() + " " + listOrder.get(0).getRawPrice());
    }

    /**
     * Put the status of the order to "prepared"
     * @param order
     */

    public void prepareOrder(Order order) {
        order.setStatus("prepared");
    }

    /**
     * Add a dish to the list
     */

    public void addDishToList() {
        List<Dish> objectDish = list.stream().filter(dishName -> dishName.getName().equals(comboBoxDish.getValue())).collect(Collectors.toList());
        Dish savedDish = objectDish.get(0);
        addedDishList.add(savedDish);
        System.out.println("List of dish updated");
    }

    /**
     * Put every order in the listView
     */

    public void displayListOrder() {
        listViewOrder.getItems().clear();
        listOrder.stream().forEach(order -> addToListview(order));
    }


    /**
     * Write the string using some order values and put it in the listView
     * @param order
     */

    public void addToListview(Order order) {
        System.out.println(order);
        System.out.println(listViewOrder.getItems());
        listViewOrder.getItems().add("Customer name : " + order.getCustomer() + " Status : " + order.getStatus());
    }

    /** ----------------------------------------------------------------------------------------------------------------
     * Initialize
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list.add(Dish.createDish("Pizza margherita", "Tomato, mozzarella, basilic", 9.00, 11.00, null));
        list.add(Dish.createDish("Spaghetti bolognese", "Pasta with bolognese sauce", 8.50, 10.00, null));
        list.add(Dish.createDish("Caesar salad", "Green salad, chicken, parmesan, croutons", 7.50, 9.00, null));

        list.stream().forEach(dish -> comboBoxDish.getItems().add(dish.getName()));
        ;
        addDishButton.setOnAction((e) -> addDishToList());

        comboBoxDishMenu.setOnMouseClicked(event -> {
            comboBoxDishMenu.getItems().clear();
            list.stream().map(Dish::getName).forEach(comboBoxDishMenu.getItems()::add);
        });

        comboBoxDishMenu.setOnAction(event -> {
            String selectedDishName = comboBoxDishMenu.getValue();
            List<Dish> matchingDishes = list.stream()
                    .filter(dish -> dish.getName().equals(selectedDishName))
                    .collect(Collectors.toList());
            if (!matchingDishes.isEmpty()) {
                Dish selectedDish = matchingDishes.get(0);
                descriptionDishMenuLabel.setText(selectedDish.getDescription());
            } else {
                descriptionDishMenuLabel.setText("");
            }
        });


        createOrderButton.setOnAction((e) -> {
            createOrder();
            displayListOrder();
        });
    }

}
