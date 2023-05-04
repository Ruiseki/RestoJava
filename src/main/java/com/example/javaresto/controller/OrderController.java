package com.example.javaresto.controller;

import com.example.javaresto.classes.Ingredient;
import com.example.javaresto.classes.Order;
import com.example.javaresto.classes.Dish;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class OrderController implements Initializable {
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
    public List<Dish> addedDishList = new ArrayList<>();
    public List<Dish> list = new ArrayList<>();
    public List<Ingredient> ingredientList = new ArrayList<>();
    public List<Order> listOrder = new ArrayList<>();

    /**
     * Delete the order in the listOrder
     * @param order
     * @param listOrder
     */

    public void deleteOrder(Order order, List<Order> listOrder) {
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
        });
    }
}