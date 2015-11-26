package com.mazzotta.kuster.pointandclick.adventure.game.elements;

import com.mazzotta.kuster.pointandclick.adventure.commands.Queue;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.items.Potion;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.items.Weapon;

import java.util.ArrayList;

public class Inventory {

    private ArrayList<Weapon> weapons;
    private ArrayList<Potion> potions;

    public Inventory() {
        weapons = new ArrayList<Weapon>();
        potions = new ArrayList<Potion>();
    }

    public void showInventory() {
        StringBuilder inventory = new StringBuilder();
        inventory.append("Inventory:\n" +
                "\nWeapons:\n");
        if(weapons.isEmpty()) {
            inventory.append("None\n");
        } else {
            for (int i = 0; i < weapons.size(); i++) {
                inventory.append("[" + (i+1) + "] " + weapons.get(i).getName() + "\n");
            }
        }

        inventory.append("\nPotions:\n");
        if(potions.isEmpty()) {
            inventory.append("None\n");
        } else {
            for (int i = 0; i < potions.size(); i++) {
                inventory.append("[" + (i+1) + "] " + potions.get(i).getName() + "\n");
            }
        }

        Queue.getInstance().addGameOutput(inventory.toString());
    }

    public String getInventoryString() {
        StringBuilder inventory = new StringBuilder();
        inventory.append("Weapons:\n");
        if(weapons.isEmpty()) {
            inventory.append("None\n");
        } else {
            for (int i = 0; i < weapons.size(); i++) {
                inventory.append("[" + (i+1) + "] " + weapons.get(i).getName() + "\n");
            }
        }

        inventory.append("\nPotions:\n");
        if(potions.isEmpty()) {
            inventory.append("None\n");
        } else {
            for (int i = 0; i < potions.size(); i++) {
                inventory.append("[" + (i+1) + "] " + potions.get(i).getName() + "\n");
            }
        }
        return inventory.toString();
    }

    public void addWeapon(Weapon weapon) {
        weapons.add(weapon);
    }

    public void addPotion(Potion potion) {
        potions.add(potion);
    }

    public ArrayList<Potion> getPotions() {
        return potions;
    }
}
