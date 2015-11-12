package com.mazzotta.kuster.pointandclick.adventure.game.elements.items;

public class Potion {

    private final String name;
    private final int healthRestorePoints;

    public Potion(String name, int healthRestorePoints) {
        this.name = name;
        this.healthRestorePoints = healthRestorePoints;
    }

    public String getName() {
        return name;
    }
    public int getHealthRestorePoints() {
        return healthRestorePoints;
    }
}
