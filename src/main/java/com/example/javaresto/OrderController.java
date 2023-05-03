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
        List<Order> savedListOrder = listOrder.stream().filter(e -> e != order).collect(Collectors.toList());
    }

    public void createOrder() {
        int totalNetPrice = addedDishList.stream().reduce(0, (result, e) -> result + e.getNetPrice(), Integer::sum);
        int totalRawPrice = addedDishList.stream().reduce(0, (result, e) -> result + e.getRawPrice(), Integer::sum);
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

    public String addDishToList() {
        Dish savedDish = list.stream().filter(e -> e.equals(comboBoxDish.getValue())).collect(Collectors.toList());
        addedDishList.add(savedDish);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonCreateOrder.setOnAction((e) -> createDishList());

        addDishButton.setOnAction((e) -> addDishToList());

    }
}