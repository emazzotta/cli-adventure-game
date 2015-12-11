package command;

import com.mazzotta.kuster.pointandclick.adventure.commands.Command;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CommandTest {


    @Before
    public void setup() {
    }

    @Test
    public void convertToCommandTest() {
        Assert.assertEquals(Command.HELP, Command.convertToCommand("Help"));
    }

    @Test
    public void convertInvalidCommandTest() {
        Assert.assertEquals(Command.NONE, Command.convertToCommand("InvalidCommandStatement"));
    }
}