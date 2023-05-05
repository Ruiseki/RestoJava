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
        table1.addOrder(new Order(new ArrayList<Dish>(), "Didier-la-Moula", "Pending", 10.01,  1.01));
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
        List restaurantRoomNameStream = Myrestaurant.getRooms().stream().map(Room::getName).toList();

        MyrestaurantRoomsComboBox.getItems().clear();
        MyrestaurantRoomsComboBox.getItems().addAll(restaurantRoomNameStream);
        refreshDisplayInformationFront();
    }


    void refreshDisplayInformationFront(){
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
        //Room1
        MyrestaurantRoom1ComboBox.getItems().clear();
        MyrestaurantRoom1ComboBox.getItems().addAll(Myrestaurant.getRooms().get(0).getTables().stream().filter(table -> table.getIsAvailable()).map(Table::idAndPlaces).map(String::valueOf).toList());

        //Room2
        MyrestaurantRoom2ComboBox.getItems().clear();
        MyrestaurantRoom2ComboBox.getItems().addAll(Myrestaurant.getRooms().get(1).getTables().stream().filter(table -> table.getIsAvailable()).map(Table::idAndPlaces).map(String::valueOf).toList());


    }
    @FXML
    void btnTableCreateClicked(ActionEvent event) {
        int NumberPlacesNewTable = Integer.parseInt(TablesPlacesNumberTextfield.getText());
        int locationRoomNewTable = MyrestaurantRoomsComboBox.getSelectionModel().getSelectedIndex();
        Room cibledroom = Myrestaurant.getRooms().get(locationRoomNewTable);
        int idNewTable = cibledroom.getTables().size() + 1;
        Table newTable = new Table(idNewTable, NumberPlacesNewTable, locationRoomNewTable);
        cibledroom.getTables().add(newTable);
        refreshDisplayInformationFront();
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
        Order order = new Order(addedDishList, textfieldName.getText(), "Pending", totalNetPrice, totalRawPrice);
        listOrder.add(order);

        // Reserve the table
        String cibledTableInfo = "";
        try {
            cibledTableInfo = MyrestaurantRoom2ComboBox.getValue();
        }catch(Exception e){
            try {
                cibledTableInfo = MyrestaurantRoom1ComboBox.getValue();
            }catch(Exception e2){
                System.out.println("No table selected");
            }
        }

        int aimTableId = Integer.parseInt(cibledTableInfo.split(" ")[1]);

        Stream cibledRoomStream = Myrestaurant.getRooms().stream();

        List cibledTableList = cibledRoomStream.flatMap(room -> ((Room) room).getTables().stream()).filter(table -> ((Table) table).getIdTable() == aimTableId).toList();
        Table cibledTable = (Table) cibledTableList.get(0);
        bookTable(order, cibledTable);
        // Display the order
        System.out.println(listOrder.get(0).getDishes() + " " + listOrder.get(0).getStatus() + " " + listOrder.get(0).getCustomer() + " " + listOrder.get(0).getNetPrice() + " " + listOrder.get(0).getRawPrice());
    }

    private void bookTable(Order order, Table cibledTable) {
        System.out.println("Table booked");
        System.out.println(order.getCustomer());
        cibledTable.addOrder(order);
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
        Myrestaurant.getRooms().stream().forEach(room -> {  // For each room
                room.getTables().stream().forEach(table ->{ // For each table
                    try{
                        addToListview(table.getOrder()); // Add the order to the list
                    }catch(Exception e){
                        System.out.println("No order in table " + table.getIdTable());
                    }
                });
        });

    }

    /**
     * Write the string using some order values and put it in the listView
     * @param order
     */

    public void addToListview(Order order) {
        System.out.println(order);
        System.out.println(listViewOrder.getItems());
        System.out.println("Customer name : " + order.getCustomer() + " Status : " + order.getStatus());
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

        createOrderButton.setOnAction((e) -> {
            createOrder();
            displayListOrder();
            refreshDisplayInformationFront();
        });
    }



}
