package com.mazzotta.kuster.pointandclick.adventure.level;

import com.mazzotta.kuster.pointandclick.adventure.game.elements.Room;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.items.Item;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.items.Potion;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.items.Weapon;

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
        addRooms();
        addItemsToRooms();
    }

    private void addItemsToRooms() {
        Weapon shortSword = new Weapon("Short Sword", 100);
        Weapon longSword = new Weapon("Long Sword", 200);
        Weapon masterSword = new Weapon("Master Sword", 500);

        Potion healthPotion50 = new Potion("Health +50", 50);
        Potion healthPotion100 = new Potion("Health +100", 100);
        Potion healthPotion150 = new Potion("Health +150", 150);

        room.get(0).addItem((Item)shortSword);
        room.get(2).addItem((Item)healthPotion50);
        room.get(2).addItem((Item)healthPotion100);
        room.get(6).addItem((Item)longSword);
        room.get(7).addItem((Item)healthPotion150);
        room.get(8).addItem((Item)masterSword);
    }

    private void addRooms() {
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
