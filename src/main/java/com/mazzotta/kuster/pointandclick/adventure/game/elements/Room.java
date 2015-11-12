package com.mazzotta.kuster.pointandclick.adventure.game.elements;

import com.mazzotta.kuster.pointandclick.adventure.game.elements.characters.Monster;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.items.Item;

import java.util.ArrayList;

public class Room {

    private String name;
    private ArrayList<Item> items;
    private Monster monster;
    private Room nextRoom;
    public boolean monsterDefeated;

    public Room(String name, Room nextRoom) {
        this.name = name;
        this.nextRoom = nextRoom;
        this.items = new ArrayList<Item>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public String getName() {
        return name;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public Monster getMonster() {
        return monster;
    }

    public Room getNextRoom() {
        return nextRoom;
    }
}
