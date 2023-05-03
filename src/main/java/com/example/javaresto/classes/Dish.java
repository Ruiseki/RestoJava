package com.example.javaresto.classes;


import javafx.scene.image.Image;
import java.util.List;

public class Dish {

    private String name;
    private String description;
    private double netPrice;

    private double grossPrice;

    private List<Dish> dishList;
    private Image image;

    public Dish(String name, String description, double netPrice, double grossPrice, List<Dish> dishList, Image image){

        this.name = name;
        this.description = description;
        this.netPrice = netPrice;
        this.grossPrice = grossPrice;
        this.dishList = dishList;
        this.image = image;

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getNetPrice() {
        return netPrice;
    }

    public double getGrossPrice() {
        return grossPrice;
    }

    public List<Dish> getDishList() {
        return dishList;
    }

    public void setDishList(List<Dish> dishList) {
        this.dishList = dishList;
    }

    public Image getImage() {
        return image;
    }

    @Override
    public String toString() {
        return name;
    }

}