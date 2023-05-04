package com.example.javaresto;

import com.example.javaresto.classes.Order;
import com.example.javaresto.classes.Dish;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class OrderController implements Initializable {
    @FXML
    private ListView listViewOrder;
    @FXML
    private MenuItem buttonCreateOrder;
    @FXML
    private ComboBox<String> comboBoxDish;
    @FXML
    private Button addDishButton;
    @FXML
    private Button createOrderButton;
    @FXML
    private TextField textfieldName;
    public List<Dish> addedDishList;
    public List list = new ArrayList();

    public void deleteOrder(Order order, List<Order> listOrder) {
        List<Order> savedListOrder = listOrder.stream().filter(currentOrder -> currentOrder != order).collect(Collectors.toList());
    }

    public void createOrder() {
        Double totalNetPrice = addedDishList.stream().reduce(0.0, (result, dish) -> result + dish.getNetPrice(), Double::sum);
        Double totalRawPrice = addedDishList.stream().reduce(0.0, (result, dish) -> result + dish.getGrossPrice(), Double::sum);
        Order order = new Order(addedDishList, textfieldName.getText(), "pending", totalNetPrice, totalRawPrice);
    }

    public void createDishList() {

        list.add("Plat 1");
        list.add("Plat 2");
        list.add("Plat 3");
        list.add("Plat 4");

        comboBoxDish.getItems().addAll(list);
    }

    public void prepareOrder(Order order) {
        order.setStatus("prepared");
    }

    public void addDishToList() {
        Dish savedDish = (Dish) list.stream().filter(dishName -> dishName.equals(comboBoxDish.getValue())).collect(Collectors.toList());
        addedDishList.add(savedDish);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonCreateOrder.setOnAction((e) -> createDishList());

        addDishButton.setOnAction((e) -> addDishToList());

    }
}