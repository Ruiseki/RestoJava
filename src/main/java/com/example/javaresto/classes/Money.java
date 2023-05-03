package com.example.javaresto.classes;

import com.example.javaresto.classes.Order;
import com.example.javaresto.classes.Dish;

import java.util.ArrayList;

public class Money {
    private static int expense = 0, gain = 0;
    private static ArrayList<Dish> dishes = new ArrayList<Dish>();

    public static int computeProfit()
    {
        return gain - expense;
    }

    public static void reset()
    {
        expense = 0;
        gain = 0;
    }

    public static void generateDocument()
    {
        // PDF here
    }

    public static void addOrder(Order order)
    {
        ArrayList<Dish> orderDishes = order.getDishes(); // -- ? --
        orderDishes.stream()
                   .forEach(element -> {
                        dishes.add(element);
                        gain += element.getPrice(); // -- ? --
                    });

    }

    public static void setProductionPrice(ArrayList<Dish> stock)
    {
        stock.stream()
             .forEach(element -> expense += element.getRawPrice()); // -- ? --
    }
}