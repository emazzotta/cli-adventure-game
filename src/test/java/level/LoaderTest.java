package level;

import com.mazzotta.kuster.clickpoint.game.commands.History;
import com.mazzotta.kuster.clickpoint.game.level.Loader;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.IOException;

import static com.mazzotta.kuster.clickpoint.game.level.FileOperationUtil.getSavePath;
import static org.junit.Assert.assertEquals;

public class LoaderTest {

    @Test
    public void testThatSavedHistoryCanBeLoaded() throws IOException {
        writeTwoCommandsToTestFile("[{\"command\":\"COLLECT\",\"actionType\":\"POTION\",\"actionIdentifier\":\"ALL\"},{\"command\":\"SHOOT\",\"actionType\":\"ENEMY\",\"actionIdentifier\":\"RED\"}]");
        new Loader().loadFrom("test");
        assertEquals(2, History.getInstance().getEnteredCommands().size());
    }

    private void writeTwoCommandsToTestFile(String dataToWrite) throws IOException {
        FileUtils.write(getSavePath("test"), dataToWrite);
    }
}
