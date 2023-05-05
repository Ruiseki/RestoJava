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
    private ComboBox<?> comboBoxEmployee1;

    @FXML
    private TextField createENameTextfield;

    @FXML
    private TextField createHourWorkedTextfield;

    @FXML
    private TextField createJobTextfield;

    @FXML
    private Button addDishButton;

    @FXML
    private Button createOrderButton;

    @FXML
    private Button preparedOrderButton;

    @FXML
    private Button deleteOrderButton;

    @FXML
    private ComboBox<String> comboBoxOrder;

    @FXML
    private Label chronoLabel;

    @FXML
    private Button chronoButtonStart;

    @FXML
    private Button chronoButtonStop;

    @FXML
    private Button chronoButtonPause;

    @FXML
    private Label gain;

    @FXML
    private Label expense;

    @FXML
    private Label profit;

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

    Chrono chrono;

    public ArrayList<Order> orderListHistory = new ArrayList<>();
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
        int idNewTable = Myrestaurant.getRooms().stream().mapToInt(room -> room.getTables().size()).sum() + 1;

        Table newTable = new Table(idNewTable, NumberPlacesNewTable, locationRoomNewTable);
        cibledroom.getTables().add(newTable);
        refreshDisplayInformationFront();
    }

    List<Employee> MyrestaurantEmployeeList = new ArrayList<>();
    @FXML
    void btnCreateEmployeeClicked(ActionEvent event) {
        String name = createENameTextfield.getText();
        String job = createJobTextfield.getText();
        String hoursWorked = createHourWorkedTextfield.getText();

        Employee newemployee = new Employee(name, job, Integer.parseInt(hoursWorked));
        MyrestaurantEmployeeList.add(newemployee);
        List employeeStream = MyrestaurantEmployeeList.stream().map(Employee::getName).toList();
        comboBoxEmployee1.getItems().clear();
        comboBoxEmployee1.getItems().addAll(employeeStream);

    }

    /**
     * Delete the employee in the listEmployee
     * @param employee
     * @param listEmployee
     */

    public void deleteEmployee(Employee employee, ArrayList<Employee> listEmployee) {
        List<Employee> savedListEmployee = listEmployee.stream().filter(currentEmployee -> currentEmployee != employee).collect(Collectors.toList());
    }

    /** ----------------------------------------------------------------------------------------------------------------
     * Order part
     */

    /**
     * Create the order calculating the net price and the raw price
     */

    public void createOrder() {
        Double totalNetPrice = addedDishList.stream().reduce(0.0, (result, dish) -> result + dish.getNetPrice(), Double::sum);
        Double totalRawPrice = addedDishList.stream().reduce(0.0, (result, dish) -> result + dish.getGrossPrice(), Double::sum);
        Order order = new Order(addedDishList, textfieldName.getText(), "Pending", totalNetPrice, totalRawPrice, orderListHistory.size()+1);
        addedDishList.clear();
        listOrder.add(order);
        orderListHistory.add(order);
        // Reserve the table
        String cibledTableInfo = "";
        int aimTableId = 0;
        try {
            cibledTableInfo = MyrestaurantRoom2ComboBox.getValue();
            aimTableId = Integer.parseInt(cibledTableInfo.split(" ")[1]);
        }catch(Exception e){
            try {
                System.out.println(MyrestaurantRoom1ComboBox.getValue());
                cibledTableInfo = MyrestaurantRoom1ComboBox.getValue();
                aimTableId = Integer.parseInt(cibledTableInfo.split(" ")[1]);
            } catch(Exception e2){
                System.out.println("No table selected");
            }
        }

        Stream cibledRoomStream = Myrestaurant.getRooms().stream();

        int finalAimTableId = aimTableId;
        List cibledTableList = cibledRoomStream.flatMap(room -> ((Room) room).getTables().stream()).filter(table -> ((Table) table).getIdTable() == finalAimTableId).toList();
        Table cibledTable = (Table) cibledTableList.get(0);
        bookTable(order, cibledTable);

    }

    private void bookTable(Order order, Table cibledTable) {
        System.out.println("Table booked");
        System.out.println(order.getCustomer());
        cibledTable.setOrder(order);
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
        comboBoxOrder.getItems().clear();

        orderListHistory.stream().forEach(order -> {
            listViewOrder.getItems().add("Order " + order.getId() + " : " + order.getCustomer()+ " for a total: " + order.getNetPrice() + "€   " + order.getStatus() );
        });

        Myrestaurant.getRooms().stream().forEach(room -> {  // For each room
                room.getTables().stream().forEach(table ->{ // For each table
                    try{

                        addToComboBoxOrder(table); // Add the order to the comboBox
                    }catch(Exception e){
                        System.out.println("No order in table " + table.getIdTable());
                    }
                });
        });
    }

    public Table actualTable () {
        String selectedOrderString = comboBoxOrder.getValue();
        selectedOrderString = selectedOrderString.split(" ")[3]; // collect Id of the table
        int selectedOrderId = Integer.parseInt(selectedOrderString);
        Stream cibledRoomStream = Myrestaurant.getRooms().stream();
        List cibledTableList = cibledRoomStream.flatMap(room -> ((Room) room).getTables().stream()).filter(table -> ((Table) table).getIdTable() == selectedOrderId).toList();
        Table cibledTable = (Table) cibledTableList.get(0);

        return cibledTable;
    }

    public Order actualOrder(){
        Table theActualTable = actualTable();
        Order order = theActualTable.getOrder();
        return order;
    }

    /**
     * Delete the order in the listOrder and in the table
     */

    public void deleteOrder() {
        Table myActualTable = actualTable();
        myActualTable.setOrder(null);
    }

    private void addToComboBoxOrder(Table table) {
        comboBoxOrder.getItems().add(table.getOrder().getCustomer() + " - Table " + table.getIdTable());
    }

    /**
     * Write the string using some order values and put it in the listView
     * @param order
     */

    public void addToListview(Order order) {
        listViewOrder.getItems().add("Customer name : " + order.getCustomer() + " Status : " + order.getStatus());
    }

    private void refreshListviewOrder() {
        listViewOrder.getItems().clear();
        displayListOrder();
    }

    /** ----------------------------------------------------------------------------------------------------------------
     * Initialize
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chrono = new Chrono(chronoLabel);

        list.add(Dish.createDish("Pizza margherita", "Tomato, mozzarella, basilic", 9.00, 11.00, null));
        list.add(Dish.createDish("Spaghetti bolognese", "Pasta with bolognese sauce", 8.50, 10.00, null));
        list.add(Dish.createDish("Caesar salad", "Green salad, chicken, parmesan, croutons", 7.50, 9.00, null));

        Money.setProductionPrice(list);
        expense.setText(Money.expense + "€");
        profit.setText(Money.computeProfit() + "€");

        list.stream().forEach(dish -> comboBoxDish.getItems().add(dish.getName()));
        ;
        addDishButton.setOnAction((e) -> addDishToList());

        /* comboBoxDishMenu.setOnMouseClicked(event -> {
            comboBoxDishMenu.getItems().clear();
            list.stream().map(Dish::getName).forEach(comboBoxDishMenu.getItems()::add);
        }); */

        /* comboBoxDishMenu.setOnAction(event -> {
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
        }); */


        createOrderButton.setOnAction((e) -> {
            createOrder();
            displayListOrder();
            refreshDisplayInformationFront();
        });

        preparedOrderButton.setOnAction((e) -> {
            prepareOrder(actualOrder());
            refreshDisplayInformationFront();
            refreshListviewOrder();
        });

        deleteOrderButton.setOnAction((e) -> {
            deleteOrder();
            refreshDisplayInformationFront();
            refreshListviewOrder();
        });


    }

    // CHRONO
    @FXML
    void chronoButtonStartClicked(ActionEvent e)
    {
        chrono.startThreaded(25 * 60 * 1000, true);
    }

    @FXML
    void chronoButtonPauseClicked(ActionEvent e)
    {
        if(chrono.isPaused())
        {
            chrono.resume();
            chronoButtonPause.setText("Pause");
        }
        else
        {
            chrono.pause();
            chronoButtonPause.setText("Resume");
        }
    }

    @FXML
    void chronoButtonStopClicked(ActionEvent e)
    {
        chrono.stop();
        chronoLabel.setText("25:00");
    }
}
