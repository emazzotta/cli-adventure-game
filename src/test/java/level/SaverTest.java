package level;

import com.mazzotta.kuster.pointandclick.adventure.commands.CommandAction;
import com.mazzotta.kuster.pointandclick.adventure.commands.History;
import com.mazzotta.kuster.pointandclick.adventure.exceptions.InvalidUserInputException;
import com.mazzotta.kuster.pointandclick.adventure.level.Saver;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static com.mazzotta.kuster.pointandclick.adventure.util.FileOperationUtil.getSavegameFile;
import static org.junit.Assert.assertEquals;

public class SaverTest {

    Saver saver;

    @Before
    public void setup() throws InvalidUserInputException {
        saver = new Saver();
        History.getInstance().addEnteredCommand(getOpenCommand());
        History.getInstance().addEnteredCommand(getCollectCommand());
    }

    @Test
    public void testThatCurrentHistoryCanBeSaved() throws IOException {
        String expectedString = "[{\"command\":\"OPEN\",\"actionType\":\"DOOR\",\"actionIdentifier\":{\"identifierId\":\"\"}},{\"command\":\"COLLECT\",\"actionType\":\"ITEMS\",\"actionIdentifier\":{\"identifierId\":\"\"}}]";
        saver.saveAs("test");
        assertEquals(expectedString, FileUtils.readFileToString(getSavegameFile("test")));
    }

    public CommandAction getOpenCommand() throws InvalidUserInputException {
        return new CommandAction(new String[]{"OPEN", "DOOR", ""});
    }

    public CommandAction getCollectCommand() throws InvalidUserInputException {
        return new CommandAction(new String[]{"COLLECT", "ITEMS", ""});
    }
}
