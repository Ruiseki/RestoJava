package com.example.javaresto.classes;

import java.util.ArrayList;

public class Money {
    public static int expense = 0, gain = 0;
    public static int computeProfit()
    {
        return gain - expense;
    }

    public static void setProductionPrice(ArrayList<Dish> stock)
    {
        stock.stream()
             .forEach(element -> expense += element.getGrossPrice());
    }
}
