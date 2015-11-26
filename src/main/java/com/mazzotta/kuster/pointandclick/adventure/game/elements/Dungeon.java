package com.mazzotta.kuster.pointandclick.adventure.game.elements;


import java.util.ArrayList;

public class Dungeon {
    private ArrayList<Room> rooms;

    public Dungeon() {
        rooms = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

}
