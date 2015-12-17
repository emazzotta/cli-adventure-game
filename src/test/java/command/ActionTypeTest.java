package command;


import com.mazzotta.kuster.pointandclick.adventure.commands.ActionType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;



public class ActionTypeTest {

    @Test
    public void testThatActionTypeConversionIsNotCaseSensitive() {
        assertEquals(ActionType.DOOR, ActionType.convertToActionType("dOOr"));
        assertEquals(ActionType.DOOR, ActionType.convertToActionType("DOOR"));
        assertEquals(ActionType.DOOR, ActionType.convertToActionType("door"));
    }

    @Test
    public void testThatDoorActionTypeIsRecognizedCorrectly() {
        assertEquals(ActionType.DOOR, ActionType.convertToActionType("door"));
    }

    @Test
    public void testThatMonsterActionTypeIsRecognizedCorrectly() {
        assertEquals(ActionType.MONSTER, ActionType.convertToActionType("monster"));
    }

    @Test
    public void testThatInventoryActionTypeIsRecognizedCorrectly() {
        assertEquals(ActionType.INVENTORY, ActionType.convertToActionType("inventory"));
    }

    @Test
    public void testThatItemsActionTypeIsRecognizedCorrectly() {
        assertEquals(ActionType.ITEMS, ActionType.convertToActionType("items"));
    }

    @Test
    public void testThatPotionActionTypeIsRecognizedCorrectly() {
        assertEquals(ActionType.POTION, ActionType.convertToActionType("potion"));
    }

    @Test
    public void testThatWeaponActionTypeIsRecognizedCorrectly() {
        assertEquals(ActionType.WEAPON, ActionType.convertToActionType("weapon"));
    }

    @Test
    public void testThatRoomActionTypeIsRecognizedCorrectly() {
        assertEquals(ActionType.ROOM, ActionType.convertToActionType("room"));
    }

    @Test
    public void testThatGameActionTypeIsRecognizedCorrectly() {
        assertEquals(ActionType.GAME, ActionType.convertToActionType("game"));
    }

    @Test
    public void testThatAnInvalidActionTypeStatementIsConvertedToNone() {
        assertEquals(ActionType.NONE, ActionType.convertToActionType("InvalidActionTypeStatement"));
    }


}