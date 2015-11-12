package com.mazzotta.kuster.pointandclick.adventure.game.elements.characters;

import com.mazzotta.kuster.pointandclick.adventure.game.elements.exception.UserDiedException;

public abstract class Character {

    public abstract void takeDamage(int damagePoints) throws UserDiedException;
    public abstract int getAttackPoints();
}
