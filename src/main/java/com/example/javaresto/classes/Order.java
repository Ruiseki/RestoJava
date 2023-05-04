package com.example.javaresto.classes;

import java.util.List;
import java.util.Optional;

public class Order {
    private List<Dish> dishes;
    private String customer;
    private String status;
    private Double netPrice;
    private Double rawPrice;

    public List<Dish> getDishes() {
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

    public Order(List<Dish> dishes, String customer, String status, Double netPrice, Double rawPrice) {
        this.dishes = dishes;
        this.customer = customer;
        this.status = status;
        this.netPrice = netPrice;
        this.rawPrice = rawPrice;
    }


}
