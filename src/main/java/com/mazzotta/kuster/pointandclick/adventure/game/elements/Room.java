package com.mazzotta.kuster.pointandclick.adventure.game.elements;

import com.mazzotta.kuster.pointandclick.adventure.game.elements.items.Item;

import java.util.ArrayList;

public class Room {

    private String name;
    private ArrayList<Item> items;

    public Room(String name) {
        this.name = name;
        items = new ArrayList<Item>();
    }

    public void addItem(Item item) {
        items.add(item);
    }
}
