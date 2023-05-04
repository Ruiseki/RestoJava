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

    public static void addIngredient(Ingredient ingredient, List<Ingredient> ingredientList) {
        ingredientList.add((Ingredient) ingredient);
    }

    public static void displayIngredient(List<Ingredient> ingredientList){
        System.out.println("Ingredient :");
        ingredientList.stream()
                .map(dish -> "- " + dish.getName() + " (" + dish.getPrice() + "$)")
                // Removing duplicates
                .distinct()
                .forEach(System.out::println);
    }

}
