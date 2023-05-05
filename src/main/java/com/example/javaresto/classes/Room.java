package com.example.javaresto.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Room {
    private int id;
    private String name;
    private int numberOfTable;
    private boolean available;
    private ArrayList<Table> tables;

    public Room(int id, String name, ArrayList<Table> tablesList, boolean available) {
        this.id = id;
        this.name = name;
        this.numberOfTable = tablesList.size();
        this.tables = tablesList;
        // true if the room is available, false if not (can be false if the room is reserved or closed)
        this.available = available;
    }

    // ------------------ //
    // GETTERS
    // ------------------ //

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getNumberOfTable() {
        return numberOfTable;
    }
    public boolean isAvailable() {
        return available;
    }
    public List<Table> getTables() {
        return tables;
    }

    // ------------------ //
    // SETTERS
    // ------------------ //

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setNumberOfTable(int numberOfTable) {
        this.numberOfTable = numberOfTable;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
    public void setTables(ArrayList<Table> tables) {
        this.tables = tables;
    }
    // options for the user to change the availability of the table
    public void changeAvailability() {
        this.available = !this.available;
    }

    // ------------------ //
    // METHODS
    // ------------------ //
    // to display the different information about the table
    public String toString() {
        return "Room " + this.id + " with " + this.numberOfTable + " tables is " + (this.available ? "available" : "not available");
    }

    // to add a table to the room
    public void addTable(int idTable, int idRoom, int places) {
        Table table = new Table(idTable, idRoom, places);
        tables.add(table);
    }

    // to remove a table from the room
    public void removeTable(int numberOfTable) {
        for (Table table : tables) {
            if (table.getIdTable() == numberOfTable) {
                tables.remove(table);
            }
        }
    }

    // to add an order to a table
    public void addOrder(int numberOfTable, Order order) {
        for (Table table : tables) {
            if (table.getIdTable() == numberOfTable) {
                table.setOrder(order);
            }
        }
    }

    // to remove an order from a table and send it to the accounting
    public Order removeOrder(int numberOfTable) {
        Order OrderPaid = null;
        Stream<Table> tableStream = tables.stream();
        Table table = tableStream.filter(reservedTable -> reservedTable.getIdTable() == numberOfTable).findFirst().orElse(null);
        if (table != null) {
            OrderPaid = table.tranfertOrder();
        }
        return OrderPaid;
    }

    public ArrayList<Table> getTables(Room room) {
        return (ArrayList<Table>) room.tables;
    }

    public int getNumberOfTableAvailable() {
        int numberOfTableAvailable = 0;
        List<Table> tables = this.getTables();
        Stream<Table> tableStream = tables.stream();
        numberOfTableAvailable = (int) tableStream.filter(table -> table.getIsAvailable()).count();
        return numberOfTableAvailable;
    }
}
