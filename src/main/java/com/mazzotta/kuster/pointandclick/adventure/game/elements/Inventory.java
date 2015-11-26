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
        inventory.append("\n\nInventory:\n");
        inventory.append("Weapons:\n");
        if(weapons.isEmpty()) {
            inventory.append(" -\n");
        } else {
            for (int i = 0; i < weapons.size(); i++) {
                inventory.append("[" + i + "]\t" + weapons.get(i).getName() + "\n");
            }
        }

        inventory.append("Potions:\n");
        if(potions.isEmpty()) {
            inventory.append(" -\n");
        } else {
            for (int i = 0; i < potions.size(); i++) {
                inventory.append("[" + i + "]\t" + potions.get(i).getName() + "\n");
            }
        }

        Queue.getInstance().addGameOutput(inventory.toString());
    }

    public void addWeapon(Weapon weapon) {
        weapons.add(weapon);
    }

    public void addPotion(Potion potion) {
        potions.add(potion);
    }

    public void discardWeapon(int index) {
        weapons.remove(index);
    }

    public void discardPotion(int index) {
        potions.remove(index);
    }
}
