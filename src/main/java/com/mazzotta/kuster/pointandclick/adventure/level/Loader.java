package com.mazzotta.kuster.pointandclick.adventure.level;

import com.google.gson.*;
import com.mazzotta.kuster.pointandclick.adventure.commands.Command;
import com.mazzotta.kuster.pointandclick.adventure.commands.CommandAction;
import com.mazzotta.kuster.pointandclick.adventure.commands.History;
import com.mazzotta.kuster.pointandclick.adventure.commands.Queue;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.mazzotta.kuster.pointandclick.adventure.commands.CommandUtil.resetGame;
import static com.mazzotta.kuster.pointandclick.adventure.commands.CommandUtil.resetGameAndDisplayInitialText;
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
        resetGame();
        ArrayList<CommandAction> commandActions = getCommandActions(commandsAsJson);

        if(commandActions.size() == 0) {
            resetGameAndDisplayInitialText();
        } else {
            for(CommandAction commandAction : commandActions) {
                History.getInstance().addEnteredCommand(commandAction);
                Queue.getInstance().addUserInput(commandAction);
            }
        }
    }

    private ArrayList<CommandAction> getCommandActions(JsonArray commandsAsJson) {
        ArrayList<CommandAction> commandActions = new ArrayList<>();
        for(JsonElement commandAsJson: commandsAsJson) {
            CommandAction commandAction = gson.fromJson(commandAsJson , CommandAction.class);
            if(!isASaveOrLoadCommand(commandAction)) {
                commandActions.add(commandAction);
            }
        }
        return commandActions;
    }

    private boolean isASaveOrLoadCommand(CommandAction commandAction) {
        return commandAction.getCommand() == Command.SAVE || commandAction.getCommand() == Command.LOAD;
    }

    private JsonArray getStringAsJsonArray(String loadedJson) {
        return new JsonParser().parse(loadedJson).getAsJsonArray();
    }
}
