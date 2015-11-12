package com.mazzotta.kuster.pointandclick.adventure.game.elements.characters;

import com.mazzotta.kuster.pointandclick.adventure.game.elements.Inventory;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.exception.UserDiedException;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.items.Weapon;

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

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }
}
