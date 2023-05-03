package com.example.javaresto.classes;

public class Table {
    private int id;
    private int places;
    private boolean available;

    public Table(int id, int seats, boolean available) {
        this.id = id;
        this.places = seats;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public int getPlaces() {
        return places;
    }

    public boolean isAvailable() {
        return available;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setPlaces(int places) {
        this.places = places;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }

    // options for the user to change the availability of the table
    public void changeAvailability() {
        this.available = !this.available;
    }

    // to display the different information about the table
    public String toString() {
        return "Table " + this.id + " with " + this.places + " seats is " + (this.available ? "available" : "not available");
    }
}
