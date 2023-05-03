package com.example.javaresto.classes;

public class Table {
    private int count = 0;
    private int idTable;
    private int places;
    private int idRoom;
    private Order order;

    public Table(int id, int places, int idRoom) {
        this.idTable = count++;     // Number of the table
        this.places = places;       // Number of places
        this.idRoom = idRoom;       // Room where the table is
        this.order = null;          // Order of the table (if there is one)
    }

    // ------------------ //
    // GETTERS
    // ------------------ //
    public int getIdTable() {
        return idTable;
    }
    public int getPlaces() {
        return places;
    }
    public int getIdRoom() {
        return idRoom;
    }
    public Order getOrder() {
        return order;
    }

    // ------------------ //
    // SETTERS
    // ------------------ //
    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    // ------------------ //
    // METHODS
    // ------------------ //
    // to display the different information about the table
    public String toString() {
        return "Table " + this.idTable + " with " + this.places + " places";
    }

    // to add an order to the table
    public void addOrder(Order order) {
        this.order = order;
    }

    // to remove an order from the table
    public Order tranfertOrder() {
        Order order = this.order;
        this.order = null;
        return order;
    }
}
