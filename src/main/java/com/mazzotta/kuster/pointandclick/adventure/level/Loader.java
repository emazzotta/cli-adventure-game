package com.mazzotta.kuster.pointandclick.adventure.level;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mazzotta.kuster.pointandclick.adventure.commands.Command;
import com.mazzotta.kuster.pointandclick.adventure.commands.CommandAction;
import com.mazzotta.kuster.pointandclick.adventure.commands.Queue;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.util.ArrayList;

import static com.mazzotta.kuster.pointandclick.adventure.util.FileOperationUtil.*;

public class Loader {

    public void loadFromJsonFile(String filename) {
        try {
            String loadedJson = FileUtils.readFileToString(getSavegameFile(filename));
            JsonArray commandsAsJson = getStringAsJsonArray(loadedJson);
            resumeGameFromCommands(commandsAsJson);
        } catch (IOException e) {
            Queue.getInstance().addGameOutput(
                    "No savegame with the name [" + filename + "] was found!\n\n" +
                    "Valid savegames are:\n" +
                    getPotentialSavegamesForFolder(getSavegameFolder()));
        }
    }

    private void resumeGameFromCommands(JsonArray commandsAsJson) {
        Initializer.getInstance().initialise();
        for(CommandAction commandAction : getCommandActionsFrom(commandsAsJson)) {
            Queue.getInstance().addUserInput(commandAction);
        }
    }

    private ArrayList<CommandAction> getCommandActionsFrom(JsonArray commandsAsJson) {
        ArrayList<CommandAction> commandActions = new ArrayList<>();
        for(JsonElement commandAsJson: commandsAsJson) {
            CommandAction commandAction = new GsonBuilder().create().fromJson(commandAsJson , CommandAction.class);
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
