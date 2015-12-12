package command;

import com.mazzotta.kuster.pointandclick.adventure.commands.Command;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommandTest {


    @Test
    public void testThatHelpCommandIsRecognizedCorrectly() {
        assertEquals(Command.HELP, Command.convertToCommand("Help"));
    }

    @Test
    public void testThatAnInvalidCommandStatementIsConvertedToNone() {
        assertEquals(Command.NONE, Command.convertToCommand("InvalidCommandStatement"));
    }
}