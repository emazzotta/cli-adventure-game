package level;

import com.mazzotta.kuster.clickpoint.game.commands.History;
import com.mazzotta.kuster.clickpoint.game.level.Loader;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static com.mazzotta.kuster.clickpoint.game.level.FileOperationUtil.getSavePath;
import static org.junit.Assert.assertEquals;

public class LoaderTest {

    Loader loader;

    @Before
    public void setup() {
        loader = new Loader();
    }

    @Test
    public void testThatSavedHistoryCanBeLoaded() throws IOException {
        assertThatTheAmountOfCommandsInHistoryIs(0);
        writeTwoCommandsToTestFile("[{\"command\":\"COLLECT\",\"actionType\":\"POTION\",\"actionIdentifier\":\"ALL\"},{\"command\":\"SHOOT\",\"actionType\":\"ENEMY\",\"actionIdentifier\":\"RED\"}]");
        loader.loadFromJsonFile("test");
        assertThatTheAmountOfCommandsInHistoryIs(2);
    }

    private void assertThatTheAmountOfCommandsInHistoryIs(int expectedAmount) {
        assertEquals(expectedAmount, History.getInstance().getEnteredCommands().size());
    }

    private void writeTwoCommandsToTestFile(String dataToWrite) throws IOException {
        FileUtils.write(getSavePath("test"), dataToWrite);
    }
}
