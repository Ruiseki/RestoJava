package com.example.javaresto.classes;

import java.util.ArrayList;
public class Order {
    private String customer;
    private String status;
    private Double netPrice;
    private int id;


    public String getCustomer() {
        return customer;
    }

    public String getStatus() {
        return status;
    }

    public Double getNetPrice() {
        return netPrice;
    }

    public int getId() {
        return id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Order(String customer, String status, Double netPrice, int id) {
        this.customer = customer;
        this.status = status;
        this.netPrice = netPrice;
        this.id = id;
    }
}