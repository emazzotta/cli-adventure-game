package game.elements;


import com.mazzotta.kuster.pointandclick.adventure.exceptions.UserDiedException;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.characters.Player;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.items.Item;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.items.Potion;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.items.Weapon;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class PlayerTest {

    Player player;

    @Before
    public void setup() {
        player = new Player(100, new Weapon("TestWeapon",  50));
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Weapon("StrongerTestWeapon", 100));
        items.add(new Weapon("StrongestTestWeapon", 5000));
        items.add(new Potion(100));
        items.add(new Potion(20));
        player.addToInventory(items);
    }

    @Test
    public void testIfPlayerTakesTheCorrectAmountOfDamage() throws UserDiedException{
        assertEquals(100, player.getHealth());
        player.takeDamage(50);
        assertEquals(50, player.getHealth());
    }

    @Test
    public void testIfNewWeaponIsCorrectlyEquipped() {
        player.setEquippedWeaponToWeaponAtPosition(2);
        assertEquals(5000, player.getAttackPoints());
    }

    @Test
    public void testIfPlayerIsAffectedWhenDrinkingAPotion() {
        assertEquals(100, player.getHealth());
        player.drinkPotionAtPosition(1);
        assertEquals(200, player.getHealth());
    }

    @Test
    public void testIfItemsAreAddedToRepository() {
        ArrayList<Item> itemsToAdd = new ArrayList<>();
        itemsToAdd.add(new Weapon("AddedWeapon1", 234));
        itemsToAdd.add(new Weapon("AddedWeapon2", 555));
        itemsToAdd.add(new Potion(55));
        itemsToAdd.add(new Potion(66));

        assertEquals(2, player.getInventory().getPotions().size());
        player.addToInventory(itemsToAdd);
        assertEquals(4, player.getInventory().getPotions().size());
    }
}
