package com.mazzotta.kuster.pointandclick.adventure.game.elements.items;

public class Weapon {

    private final int attackPoints;
    private final String name;

    public Weapon(String name, int attackPoints) {
        this.name = name;
        this.attackPoints = attackPoints;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public String getName() {
        return name;
    }
}
