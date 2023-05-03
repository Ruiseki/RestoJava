package com.example.javaresto.classes;

public class Room {
    private int id;
    private int numberOfTable;
    private boolean available;

    public Room(int id, int numberOfTable, boolean available) {
        this.id = id;
        this.numberOfTable = numberOfTable;
        this.available = available;
    }

    public int getId() {
        return id;
    }
    public int getNumberOfTable() {
        return numberOfTable;
    }
    public boolean isAvailable() {
        return available;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setNumberOfTable(int numberOfTable) {
        this.numberOfTable = numberOfTable;
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
        return "Room " + this.id + " with " + this.numberOfTable + " tables is " + (this.available ? "available" : "not available");
    }
}
