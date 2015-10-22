package level;

import com.mazzotta.kuster.clickpoint.game.commands.CommandAction;
import com.mazzotta.kuster.clickpoint.game.commands.History;
import com.mazzotta.kuster.clickpoint.game.commands.parsing.InvalidUserInputException;
import com.mazzotta.kuster.clickpoint.game.level.Saver;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SaverTest {

    Saver saver;

    @Before
    public void setup() throws InvalidUserInputException {
        saver = new Saver();
        History.getInstance().addEnteredCommand(getCollectCommand());
        History.getInstance().addEnteredCommand(getShootCommand());
    }

    @Test
    public void testThatCurrentHistoryCanBeSaved() throws IOException {
        String expectedString = "history: [{\"command\":\"COLLECT\",\"actionType\":\"POTION\",\"actionIdentifier\":\"ALL\"},{\"command\":\"SHOOT\",\"actionType\":\"ENEMY\",\"actionIdentifier\":\"RED\"},]";
        saver.saveAs("test");
        assertEquals(expectedString, FileUtils.readFileToString(saver.getSavePath("test")));
    }

    public CommandAction getShootCommand() throws InvalidUserInputException {
        return new CommandAction(new String[]{"SHOOT", "ENEMY", "RED"});
    }

    public CommandAction getCollectCommand() throws InvalidUserInputException {
        return new CommandAction(new String[]{"COLLECT", "POTION", "ALL"});
    }
}
