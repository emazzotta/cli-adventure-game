package com.mazzotta.kuster.pointandclick.adventure.game.elements;

import com.mazzotta.kuster.pointandclick.adventure.commands.Queue;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.items.Potion;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.items.Weapon;

import java.util.ArrayList;

public class Inventory {

    private ArrayList<Weapon> weapons;
    private ArrayList<Potion> potions;

    public Inventory() {
        weapons = new ArrayList<>();
        potions = new ArrayList<>();
    }

    public void showInventory() {
        StringBuilder inventory = new StringBuilder("Inventory:\n\nWeapons:\n");
        if(weapons.isEmpty()) {
            inventory.append("None\n");
        } else {
            for(int i = 0; i < weapons.size(); i++) {
                inventory.append("[").append(i + 1).append("] ").append(weapons.get(i).getName()).append("\n");
            }
        }

        inventory.append("\nPotions:\n");
        if(potions.isEmpty()) {
            inventory.append("None\n");
        } else {
            for(int i = 0; i < potions.size(); i++) {
                inventory.append("[").append(i + 1).append("] ").append(potions.get(i).getName()).append("\n");
            }
        }

        Queue.getInstance().addGameOutput(inventory.toString());
    }

    public String getInventoryString() {
        StringBuilder inventory = new StringBuilder("Weapons:\n");
        if(weapons.isEmpty()) {
            inventory.append("None\n");
        } else {
            for(int i = 0; i < weapons.size(); i++) {
                inventory.append("[").append(i + 1).append("] ").append(weapons.get(i).getName()).append("\n");
            }
        }

        inventory.append("\nPotions:\n");
        if(potions.isEmpty()) {
            inventory.append("None\n");
        } else {
            for(int i = 0; i < potions.size(); i++) {
                inventory.append("[").append(i + 1).append("] ").append(potions.get(i).getName()).append("\n");
            }
        }
        return inventory.toString();
    }

    public void addWeapon(Weapon weapon) {
        weapons.add(weapon);
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public void addPotion(Potion potion) {
        potions.add(potion);
    }

    public ArrayList<Potion> getPotions() {
        return potions;
    }
}
