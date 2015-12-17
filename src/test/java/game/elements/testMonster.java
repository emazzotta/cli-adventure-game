package game.elements;

import com.mazzotta.kuster.pointandclick.adventure.game.elements.characters.Monster;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class testMonster {

    Monster monster;

    @Before
    public void setup() {
        monster = new Monster("TestMonster", 100, 100);
    }

    @Test
    public void testIfMonsterIsAliveWhenHealthIsGreaterThanZero() {
        assertEquals(true, monster.isAlive());
    }

    @Test
    public void testIfMonsterIsNotAliveIfHealthIsBelowOrEqualToZero() {
        monster.takeDamage(100);
        assertEquals(false, monster.isAlive());
    }

    @Test
    public void testIfMonsterTakesDamage() {
        assertEquals(100, monster.getHealth());
        monster.takeDamage(50);
        assertEquals(50, monster.getHealth());
    }
}
