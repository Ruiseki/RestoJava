package com.example.javaresto.classes;


import com.example.javaresto.classes.Ingredient;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Dish extends Ingredient {

    private String name;
    private String description;
    private double netPrice;
    private double grossPrice;
    private List<Dish> dishList;
    private Image image;

    public Dish(String name, String description, double netPrice, double grossPrice, List<Dish> dishList, Image image){
        super(name, grossPrice);

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

    /*public static Dish createDish(String name, String description, double netPrice, double grossPrice, Image image) {
        List<Dish> dishList = new ArrayList<>();
        dishList.add(new Dish(name, description, netPrice, grossPrice, image));
        return new Dish(name, description, netPrice, grossPrice, image);
    }*/


}