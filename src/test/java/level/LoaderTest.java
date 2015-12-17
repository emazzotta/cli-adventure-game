package level;

import com.mazzotta.kuster.pointandclick.adventure.commands.Queue;
import com.mazzotta.kuster.pointandclick.adventure.level.Loader;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static com.mazzotta.kuster.pointandclick.adventure.util.FileOperationUtil.getSavegameFile;
import static org.junit.Assert.assertEquals;

public class LoaderTest {

    Loader loader;

    @Before
    public void setup() {
        loader = new Loader();
    }

    @Test
    public void testThatSavedHistoryCanBeLoaded() throws IOException {
        assertThatTheAmountOfCommandsInPendingUserInputIs(0);
        writeTwoCommandsToTestFile("[{\"command\":\"OPEN\",\"actionType\":\"DOOR\",\"actionIdentifier\":{\"identifierId\":\"\"}},{\"command\":\"COLLECT\",\"actionType\":\"ITEMS\",\"actionIdentifier\":{\"identifierId\":\"\"}}]");
        loader.loadFromJsonFile("test");
        assertThatTheAmountOfCommandsInPendingUserInputIs(2);
    }

    private void assertThatTheAmountOfCommandsInPendingUserInputIs(int expectedAmount) {
        assertEquals(expectedAmount, Queue.getInstance().getPendingUserInput().size());
    }

    private void writeTwoCommandsToTestFile(String dataToWrite) throws IOException {
        FileUtils.write(getSavegameFile("test"), dataToWrite);
    }
}
