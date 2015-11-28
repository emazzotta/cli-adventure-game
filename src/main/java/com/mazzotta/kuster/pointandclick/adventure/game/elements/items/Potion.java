package com.mazzotta.kuster.pointandclick.adventure.game.elements.items;

public class Potion implements Item {

    private final int healthRestorePoints;

    public Potion(int healthRestorePoints) {
        this.healthRestorePoints = healthRestorePoints;
    }

    public String getName() {
        return "Health Potion +" + healthRestorePoints;
    }
    public int getHealthRestorePoints() {
        return healthRestorePoints;
    }
}
