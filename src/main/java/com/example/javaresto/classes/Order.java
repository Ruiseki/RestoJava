package com.example.javaresto.classes;

import java.util.ArrayList;
import java.util.Optional;

public class Order {
    private ArrayList<Dish> dishes;
    private String customer;
    private String status;
    private Double netPrice;
    private Double rawPrice;

    public ArrayList<Dish> getDishes() {
        return dishes;
    }

    public String getCustomer() {
        return customer;
    }

    public String getStatus() {
        return status;
    }

    public Double getNetPrice() {
        return netPrice;
    }

    public Double getRawPrice() {
        return rawPrice;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Order(ArrayList<Dish> dishes, String customer, String status, Double netPrice, Double rawPrice, int id) {
        this.dishes = dishes;
        this.customer = customer;
        this.status = status;
        this.netPrice = netPrice;
        this.rawPrice = rawPrice;
        this.id = id;
    }
}