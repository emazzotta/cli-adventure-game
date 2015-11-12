package com.mazzotta.kuster.pointandclick.adventure.game.elements;

public class Monster {

    private final String name;
    private final int attackPoints;
    private int health;

    public Monster(String name, int attackPoints, int health) {
        this.name = name;
        this.attackPoints = attackPoints;
        this.health = health;
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
        if(health - damagePoints <= 0) {
            System.out.printf("Monster is dead");
        }
        reduceHealthPointsBy(damagePoints);
    }

    private void reduceHealthPointsBy(int damagePoints) {
        health -= damagePoints;
    }

    public boolean isAlive() {
        return health > 0;
    }
}
