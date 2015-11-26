package com.mazzotta.kuster.pointandclick.adventure.level;

import com.google.gson.*;
import com.mazzotta.kuster.pointandclick.adventure.commands.Command;
import com.mazzotta.kuster.pointandclick.adventure.commands.CommandAction;
import com.mazzotta.kuster.pointandclick.adventure.commands.History;
import com.mazzotta.kuster.pointandclick.adventure.commands.Queue;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.UserState;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

import static com.mazzotta.kuster.pointandclick.adventure.util.FileOperationUtil.*;

public class Loader {

    Gson gson;

    public Loader() {
        gson = new GsonBuilder().create();
    }

    public void loadFromJsonFile(String filename) {
        try {
            String loadedJson = FileUtils.readFileToString(getSaveGameFile(filename));
            JsonArray commandsAsJson = getStringAsJsonArray(loadedJson);
            resumeGameFromCommands(commandsAsJson);
        } catch (IOException e) {
            Queue.getInstance().addGameOutput("No savegame with the name [" + filename + "] was found!\n\n" +
                    "Valid savegames are:\n" +
                    listFilesForFolder(new File(getSaveGameFolder())));
        }
    }

    private void resumeGameFromCommands(JsonArray commandsAsJson) {
        History.getInstance().clearCommands();
        UserState.getInstance().resetUserState();
        for(JsonElement commandAsJson: commandsAsJson) {
            CommandAction commandAction = gson.fromJson(commandAsJson , CommandAction.class);
            if(!isASaveOrLoadCommand(commandAction)) {
                History.getInstance().addEnteredCommand(commandAction);
                Queue.getInstance().addUserInput(commandAction);
            }
        }
    }

    private boolean isASaveOrLoadCommand(CommandAction commandAction) {
        return commandAction.getCommand() == Command.SAVE || commandAction.getCommand() == Command.LOAD;
    }

    private JsonArray getStringAsJsonArray(String loadedJson) {
        return new JsonParser().parse(loadedJson).getAsJsonArray();
    }
}
