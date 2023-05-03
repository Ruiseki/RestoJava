package com.example.javaresto.classes;

import java.util.List;

public class Room {
    private String id;
    private int numberOfTable;
    private boolean available;
    private List<Table> tables;

    public Room(String id, int numberOfTable, boolean available) {
        this.id = id;
        this.numberOfTable = numberOfTable;
        // true if the room is available, false if not (can be false if the room is reserved or closed)
        this.available = available;
    }

    // ------------------ //
    // GETTERS
    // ------------------ //
    public String getId() {
        return id;
    }
    public int getNumberOfTable() {
        return numberOfTable;
    }
    public boolean isAvailable() {
        return available;
    }

    // ------------------ //
    // SETTERS
    // ------------------ //
    public void setId(String id) {
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

    public void addTable(int idTable, int idRoom, int places) {
        Table table = new Table(idTable, idRoom, places);
        tables.add(table);
    }

    public void removeTable(int numberOfTable) {
        for (Table table : tables) {
            if (table.getIdTable() == numberOfTable) {
                tables.remove(table);
            }
        }
    }


}
