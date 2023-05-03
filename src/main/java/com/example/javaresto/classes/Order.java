package com.example.javaresto.classes;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Order {
    private List<Dish> dishes;
    private String customer;
    private String status;
    private int netPrice;
    private int rawPrice;

    public List<Dish> getDishes() {
        return dishes;
    }

    public String getCustomer() {
        return customer;
    }

    public String getStatus() {
        return status;
    }

    public int getNetPrice() {
        return netPrice;
    }

    public int getRawPrice() {
        return rawPrice;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Order(List<Dish> dishes, String customer, String status, int netPrice, int rawPrice) {
        this.dishes = dishes;
        this.customer = customer;
        this.status = status;
        this.netPrice = netPrice;
        this.rawPrice = rawPrice;
    }


}
