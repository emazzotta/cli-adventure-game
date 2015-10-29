package com.mazzotta.kuster.pointandclick.adventure.game.elements;

import com.mazzotta.kuster.pointandclick.adventure.game.elements.exception.UserDiedException;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.items.Weapon;

public class Player {

    private Weapon equippedWeapon;
    private Inventory inventory;
    private int health;

    public Player() {
    }

    public void takeDamage(int damagePoints) throws UserDiedException {
        if(health - damagePoints <= 0) {
            throw new UserDiedException("Game Over! You died.");
        }
        reduceHealthPointsBy(damagePoints);
    }

    private void reduceHealthPointsBy(int damagePoints) {
        health -= damagePoints;
    }
}
