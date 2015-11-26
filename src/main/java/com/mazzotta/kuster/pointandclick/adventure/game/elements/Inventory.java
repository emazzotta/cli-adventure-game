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
        String inventory = "Inventory:\n\nWeapons:\n";
        if(weapons.isEmpty()) {
            inventory += "None\n";
        } else {
            for(int i = 0; i < weapons.size(); i++) {
                inventory += "[" + (i+1) + "] " + weapons.get(i).getName() + "\n";
            }
        }

        inventory += "\nPotions:\n";
        if(potions.isEmpty()) {
            inventory += "None\n";
        } else {
            for(int i = 0; i < potions.size(); i++) {
                inventory += "[" + (i+1) + "] " + potions.get(i).getName() + "\n";
            }
        }

        Queue.getInstance().addGameOutput(inventory);
    }

    public String getInventoryString() {
        String inventory = "Weapons:\n";
        if(weapons.isEmpty()) {
            inventory += "None\n";
        } else {
            for(int i = 0; i < weapons.size(); i++) {
                inventory += "[" + (i+1) + "] " + weapons.get(i).getName() + "\n";
            }
        }

        inventory += "\nPotions:\n";
        if(potions.isEmpty()) {
            inventory += "None\n";
        } else {
            for(int i = 0; i < potions.size(); i++) {
                inventory += "[" + (i+1) + "] " + potions.get(i).getName() + "\n";
            }
        }
        return inventory;
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
