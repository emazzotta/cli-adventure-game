package command;

import com.mazzotta.kuster.pointandclick.adventure.commands.Command;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommandTest {
    @Test
    public void testThatCommandConversionIsNotCaseSensitive() {
        assertEquals(Command.HELP, Command.convertToCommand("hELp"));
        assertEquals(Command.HELP, Command.convertToCommand("HELP"));
        assertEquals(Command.HELP, Command.convertToCommand("help"));
    }

    @Test
    public void testThatHelpCommandIsRecognizedCorrectly() {
        assertEquals(Command.HELP, Command.convertToCommand("help"));
    }

    @Test
    public void testThatAttackCommandIsRecognizedCorrectly() {
        assertEquals(Command.ATTACK, Command.convertToCommand("attack"));
    }

    @Test
    public void testThatCollectCommandIsRecognizedCorrectly() {
        assertEquals(Command.COLLECT, Command.convertToCommand("collect"));
    }

    @Test
    public void testThatHistoryCommandIsRecognizedCorrectly() {
        assertEquals(Command.HISTORY, Command.convertToCommand("history"));
    }

    @Test
    public void testThatInspectCommandIsRecognizedCorrectly() {
        assertEquals(Command.INSPECT, Command.convertToCommand("inspect"));
    }

    @Test
    public void testThatLoadCommandIsRecognizedCorrectly() {
        assertEquals(Command.LOAD, Command.convertToCommand("load"));
    }

    @Test
    public void testThatSaveCommandIsRecognizedCorrectly() {
        assertEquals(Command.SAVE, Command.convertToCommand("save"));
    }

    @Test
    public void testThatOpenCommandIsRecognizedCorrectly() {
        assertEquals(Command.OPEN, Command.convertToCommand("open"));
    }

    @Test
    public void testThatResetCommandIsRecognizedCorrectly() {
        assertEquals(Command.RESET, Command.convertToCommand("reset"));
    }

    @Test
    public void testThatUseCommandIsRecognizedCorrectly() {
        assertEquals(Command.USE, Command.convertToCommand("use"));
    }

    @Test
    public void testThatAnInvalidCommandStatementIsConvertedToNone() {
        assertEquals(Command.NONE, Command.convertToCommand("InvalidCommandStatement"));
    }
}