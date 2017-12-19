package com.mazzotta.kuster.pointandclick.adventure.game.elements;

import com.mazzotta.kuster.pointandclick.adventure.game.elements.characters.Monster;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.items.Item;

import java.util.ArrayList;

public class Room {

    private String name;
    private ArrayList<Item> items;
    private Monster monster;
    private Room nextRoom;

    public Room(String name, Room nextRoom) {
        this.name = name;
        this.nextRoom = nextRoom;
        this.items = new ArrayList<>();
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

    public boolean hasNextRoom() {
        return nextRoom != null;
    }

    public boolean hasMonster() {
        return monster != null;
    }

    public String getRoomContent() {
        StringBuilder roomContent = new StringBuilder("Room: " + name + "\n");
        if(hasMonster()) {
            roomContent.append("Monster: ").append(monster.getName()).append("\n");
            roomContent.append("Alive: ").append(monster.isAlive()).append("\n");
        }
        if(!items.isEmpty()) {
            roomContent.append("Items:\n");
            for(int i = 0; i < items.size(); i++) {
                roomContent.append("[").append(i + 1).append("] ").append(items.get(i).getName()).append("\n");
            }
        } else {
            roomContent.append("There are no items in this room (anymore)");
        }
        return roomContent.toString();
    }

    public boolean hasUndefeatedMonster() {
        return hasMonster() && getMonster().isAlive();
    }

    public void removeAllItems() {
        items.clear();
    }
}
