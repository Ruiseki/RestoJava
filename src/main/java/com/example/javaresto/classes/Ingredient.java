package com.example.javaresto.classes;

import java.util.ArrayList;
import java.util.List;

public class Ingredient {

    private String name;
    private double price;


    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Ingredient(String name, double price) {
        this.name = name;
        this.price = price;
    }


    public static Ingredient createIngredient(String name, double price) {
        Ingredient ingredient = new Ingredient(name, price);
        return ingredient;
    }

}
