package com.example.javaresto.classes;

public class Restaurant {
    private String name;
    private String address;
    private String description;

    public Restaurant(String name, String address, String description) {
        this.name = name;
        this.address = address;
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    // to display the different information about the restaurant
    public String toString() {
        return "Restaurant " + this.name + " is located at " + this.address + ". " + this.description;
    }
}
