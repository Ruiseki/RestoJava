package com.example.javaresto.classes;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String address;
    private String description;
    private List<Room> rooms;

    public Restaurant(String name, String address, String description) {
        this.name = name;
        this.address = address;
        this.description = description;
        // By default, we create 2 rooms
        ArrayList<Room> places = new ArrayList<Room>();
        Room terrace = new Room(1,"Terrace", new ArrayList<Table>(), true);
        places.add(terrace);
        Room Mainroom = new Room(2,"MainRoom", new ArrayList<Table>(), true);
        places.add(Mainroom);
        this.rooms = places;
    }

    // ------------------ //
    // GETTERS
    // ------------------ //
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public String getDescription() {
        return description;
    }
    public List<Room> getRooms() { return rooms; }

    // ------------------ //
    // SETTERS
    // ------------------ //
    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    // ------------------ //
    // METHODS
    // ------------------ //
    // to display the different information about the restaurant
    public String toString() {
        return "Restaurant " + this.name + " is located at " + this.address + ". " + this.description;
    }

    //-------------------//
    // ROOMS
    //-------------------//
    // create a new room
    public void createRoom(int id, String name, List<Table> tablesList, boolean available) {
        Room room = new Room(id,name, tablesList, available);
        this.rooms.add(room);
    }
    // to add a room to the restaurant
    public void addRoom(Room room) {
        this.rooms.add(room);
    }
    // to remove a room from the restaurant
    public void removeRoom(Room room) {
            this.rooms.remove(room);
    }
}
