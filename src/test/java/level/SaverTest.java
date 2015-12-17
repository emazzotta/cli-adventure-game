package level;

import com.mazzotta.kuster.pointandclick.adventure.commands.CommandAction;
import com.mazzotta.kuster.pointandclick.adventure.commands.History;
import com.mazzotta.kuster.pointandclick.adventure.exceptions.InvalidUserInputException;
import com.mazzotta.kuster.pointandclick.adventure.level.Saver;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

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
        ArrayList<String> openCommand = new ArrayList<>();
        openCommand.add("OPEN");
        openCommand.add("DOOR");
        return new CommandAction(openCommand);
    }

    public CommandAction getCollectCommand() throws InvalidUserInputException {
        ArrayList<String> collectCommand = new ArrayList<>();
        collectCommand.add("COLLECT");
        collectCommand.add("ITEMS");
        return new CommandAction(collectCommand);
    }
}
