package com.mazzotta.kuster.pointandclick.adventure.game.elements.characters;

import com.mazzotta.kuster.pointandclick.adventure.game.elements.items.Item;

import java.util.ArrayList;
import java.util.Collections;

public class Monster extends Character {

    private final String name;
    private final int attackPoints;
    private int health;
    private ArrayList<Item> loot;

    public Monster(String name, int attackPoints, int health, Item... loot) {
        this.name = name;
        this.attackPoints = attackPoints;
        this.health = health;
        this.loot = new ArrayList<>();
        Collections.addAll(this.loot, loot);
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void takeDamage(int damagePoints) {
        reduceHealthPointsBy(damagePoints);
    }

    private void reduceHealthPointsBy(int damagePoints) {
        health -= damagePoints;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public ArrayList<Item> getLoot() {
        return loot;
    }
}
