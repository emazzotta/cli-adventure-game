package com.mazzotta.kuster.pointandclick.adventure.game.elements;

import com.mazzotta.kuster.pointandclick.adventure.game.elements.characters.Character;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.exception.UserDiedException;

public class Combat {

    public void attack(Character attacker, Character defender) throws UserDiedException {
        defender.takeDamage(attacker.getAttackPoints());
    }
}
