package com.example.javaresto.classes;

public class Table {
    private int idTable;
    private int places;
    private int idRoom;
    private Order order;

    public Table(int id, int places, int idRoom) {
        this.idTable = id;     // Number of the table
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

    public String idAndPlaces() {
        return "Table " + this.getIdTable() + " with " + this.getPlaces() + " places";
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
    public void setOrder(Order order) {
        this.order = order;
    }

    // ------------------ //
    // METHODS
    // ------------------ //
    // to display the different information about the table
    public String toString() {
        return "Table " + this.idTable + " with " + this.places + " places";
    }

    // to remove an order from the table
    public Order tranfertOrder() {
        Order order = this.order;
        this.order = null;
        return order;
    }

    // to change the number of places of the table
    public void changePlaces(int places) {
        this.places = places;
    }

    public boolean getIsAvailable() {
        if (this.order == null) {
            return true;
        }
        return false;
    }
}
