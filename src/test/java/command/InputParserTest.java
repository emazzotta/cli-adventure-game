package command;

import com.mazzotta.kuster.pointandclick.adventure.commands.CommandAction;
import com.mazzotta.kuster.pointandclick.adventure.commands.parsing.InputParser;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class InputParserTest {

    @Test
    public void testThatUserInputCanBeCorrectlyParsedToCommandActionObject() {
        String command = "collect items";
        CommandAction commandAction = new InputParser().getCommandActionFrom(command);
        assertEquals(commandAction.getCommand().toString(), "COLLECT");
        assertEquals(commandAction.getActionType().toString(), "ITEMS");
    }
}
