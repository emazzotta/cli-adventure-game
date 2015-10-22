package level;

import com.mazzotta.kuster.clickpoint.game.commands.CommandAction;
import com.mazzotta.kuster.clickpoint.game.commands.History;
import com.mazzotta.kuster.clickpoint.game.commands.parsing.InvalidUserInputException;
import com.mazzotta.kuster.clickpoint.game.level.Saver;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class SaverTest {

    public void setup() throws InvalidUserInputException {
        History.getInstance().addEnteredCommand(getCollectCommand());
        History.getInstance().addEnteredCommand(getShootCommand());
    }

    @Test
    public void testThatCurrentHistoryCanBeSaved() throws InvalidUserInputException {
        new Saver().saveAs("test");
        //assertEquals(FileUtils.readFileToString())
    }

    public CommandAction getShootCommand() throws InvalidUserInputException {
        return new CommandAction(new String[]{"SHOOT", "ENEMY", "RED"});
    }

    public CommandAction getCollectCommand() throws InvalidUserInputException {
        return new CommandAction(new String[]{"COLLECT", "POTION", "ALL"});
    }
}
