package com.mazzotta.kuster.pointandclick.adventure.game.elements.characters;

import com.mazzotta.kuster.pointandclick.adventure.commands.Queue;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.Inventory;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.exception.UserDiedException;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.items.Item;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.items.Potion;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.items.Weapon;

import java.util.ArrayList;

public class Player extends Character {

    private Weapon equippedWeapon;
    private Inventory inventory;
    private String name;
    private int health;

    public Player(String name, int health, Weapon equippedWeapon) {
        inventory = new Inventory();
        this.name = name;
        this.health = health;
        this.equippedWeapon = equippedWeapon;
    }

    public void takeDamage(int damagePoints) throws UserDiedException {
        if(health - damagePoints <= 0) {
            throw new UserDiedException("Game Over! You died.");
        }
        reduceHealthPointsBy(damagePoints);
    }

    public int getAttackPoints() {
        return equippedWeapon.getAttackPoints();
    }

    private void reduceHealthPointsBy(int damagePoints) {
        health -= damagePoints;
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(Weapon equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void drinkPotion(int potionPosition) {
        try {
            int healthPoints = inventory.getPotions().get(potionPosition-1).getHealthRestorePoints();
            health += healthPoints;
            inventory.getPotions().remove(potionPosition-1);
            Queue.getInstance().addGameOutput("Health restored [+" + healthPoints + "]! New health: " + health);
        } catch (Exception e) {
            Queue.getInstance().addGameOutput("Potion number [" + potionPosition +"] does not exist!");
        }
    }

    public void addToInventory(ArrayList<Item> items) {
        for(Item item : items) {
            if (item instanceof Weapon) {
                inventory.addWeapon((Weapon) item);
            } else if (item instanceof Potion) {
                inventory.addPotion((Potion) item);
            }
        }
        Queue.getInstance().addGameOutput("You have collected this room's items\n" +
                "\nNew Inventory:\n" +
                inventory.getInventoryString());
    }

    public int getHealth() {
        return health;
    }
}
