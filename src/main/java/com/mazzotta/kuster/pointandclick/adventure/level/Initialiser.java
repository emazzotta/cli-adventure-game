package com.mazzotta.kuster.pointandclick.adventure.level;

import com.mazzotta.kuster.pointandclick.adventure.game.elements.Room;

import java.util.ArrayList;

public class Initialiser {

    private ArrayList<Room> room;
    private static Initialiser instance;

    private Initialiser() {
        room = new ArrayList<Room>();
    }

    public static Initialiser getInstance() {
        if(instance == null) {
            instance = new Initialiser();
        }
        return instance;
    }


    public void initialise() {
        room.add(new Room("Room 1"));
        room.add(new Room("Room 2"));
        room.add(new Room("Room 3"));
        room.add(new Room("Room 4"));
        room.add(new Room("Room 5"));
        room.add(new Room("Room 6"));
        room.add(new Room("Room 7"));
        room.add(new Room("Room 8"));
        room.add(new Room("Room 9"));
    }
}
