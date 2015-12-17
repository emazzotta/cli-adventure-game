package command;

import com.mazzotta.kuster.pointandclick.adventure.commands.ActionType;
import com.mazzotta.kuster.pointandclick.adventure.commands.Command;
import com.mazzotta.kuster.pointandclick.adventure.commands.CommandAction;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CommandActionTest {

    @Test
    public void testThatACommandActionFromOneArgumentIsCreatedCorrectly() {
        ArrayList<String> commandActionFragments = new ArrayList<>();
        commandActionFragments.add("Use");
        CommandAction commandAction = new CommandAction(commandActionFragments);

        assertEquals(Command.USE, commandAction.getCommand());
    }

    @Test
    public void testThatACommandACtionFromTwoArgumentsIsCreatedCorrectly() {
        ArrayList<String> commandActionFragments = new ArrayList<>();
        commandActionFragments.add("Use");
        commandActionFragments.add("Potion");
        CommandAction commandAction = new CommandAction(commandActionFragments);

        assertEquals(Command.USE, commandAction.getCommand());
        assertEquals(ActionType.POTION, commandAction.getActionType());
    }

    @Test
    public void testThatACommandActionFromThreeArgumentsIsCreatedCorrectly() {
        ArrayList<String> commandActionFragments = new ArrayList<>();
        commandActionFragments.add("Use");
        commandActionFragments.add("Potion");
        commandActionFragments.add("1");
        CommandAction commandAction = new CommandAction(commandActionFragments);

        assertEquals(Command.USE, commandAction.getCommand());
        assertEquals(ActionType.POTION, commandAction.getActionType());
        assertEquals("1", commandAction.getActionIdentifier().getIdentifierId());
    }

    @Test
    public void testThatNoMoreThanThreeInputArgumentsAreHandled() {
        ArrayList<String> commandActionFragments = new ArrayList<>();
        commandActionFragments.add("Use");
        commandActionFragments.add("Potion");
        commandActionFragments.add("1");
        commandActionFragments.add("UeberfluessigeEingabe");
        CommandAction commandAction = new CommandAction(commandActionFragments);

        assertEquals(Command.USE, commandAction.getCommand());
        assertEquals(ActionType.POTION, commandAction.getActionType());
        assertEquals("1", commandAction.getActionIdentifier().getIdentifierId());
    }

    @Test
    public void testThatAnEmptyInputIsHandledCorrectly() {
        ArrayList<String> commandActionFragments = new ArrayList<>();
        CommandAction commandAction = new CommandAction(commandActionFragments);

        assertEquals(Command.NONE, commandAction.getCommand());
        assertEquals(ActionType.NONE, commandAction.getActionType());
        assertEquals("", commandAction.getActionIdentifier().getIdentifierId());
    }

}
