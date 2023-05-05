package com.example.javaresto.classes;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Dish extends Ingredient {

    private String name;
    private String description;
    private double netPrice;
    private double grossPrice;
    private Image image;
    private static ArrayList<Dish> dishList = new ArrayList<>();

    public Dish(String name, String description, double netPrice, double grossPrice, Image image) {
        super(name, grossPrice);
        this.name = name;
        this.description = description;
        this.netPrice = netPrice;
        this.grossPrice = grossPrice;
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

    public Image getImage() {
        return image;
    }


    public static Dish createDish(String name, String description, double netPrice, double grossPrice, Image image) {
        Dish dish = new Dish(name, description, netPrice, grossPrice, image);
        return dish;
    }

    public static void addDish(Dish dish) {
        dishList.add(dish);
    }

    public static void displayMenu() {
        System.out.println("Menu :");
        dishList.stream()
                .map(dish -> "- " + dish.getName() + " (" + dish.getNetPrice() + "$)")
                // Removing duplicates
                .distinct()
                .forEach(System.out::println);
    }

    public static void displayDishDetails(String dishName) {

        // Search for the entered dish if it matches the one in my list
        Optional<Dish> optionalDish = dishList.stream()
                .filter(dish -> dish.getName().equalsIgnoreCase(dishName))
                .findFirst();

        // If it exists in the list
        if (optionalDish.isPresent()) {
            Dish dish = optionalDish.get();
            System.out.println(dish.getName() + ": " + dish.getDescription());
            System.out.println("Net Price: " + dish.getNetPrice() + "$");
            System.out.println("Gross price: " + dish.getGrossPrice() + "$");
        } else {
            System.out.println("The dish '" + dishName + "' does not exist in the menu.");
        }
    }

    public static double calculateNetPrice(double grossPrice) {
        double netPrice = grossPrice * 1.1;
        return Math.round(netPrice * 100.0) / 100.0;
    }


}