package com.mazzotta.kuster.clickpoint.game.level;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mazzotta.kuster.clickpoint.game.commands.CommandAction;
import com.mazzotta.kuster.clickpoint.game.commands.History;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.mazzotta.kuster.clickpoint.game.level.FileOperationUtil.getSavePath;

public class Saver {

    Gson gson;

    public Saver() {
        gson = new GsonBuilder().create();
    }

    public void saveAs(String filename) {
        File savePath = getSavePath(filename);
        StringBuilder saveDataString = buildJsonFromHistory();

        try {
            FileUtils.write(savePath, saveDataString.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private StringBuilder buildJsonFromHistory() {
        StringBuilder saveDataString = new StringBuilder("history: [");
        ArrayList<CommandAction> enteredCommands = History.getInstance().getEnteredCommands();
        for (int i = 0; i < enteredCommands.size(); i++) {
            saveDataString.append(gson.toJson(enteredCommands.get(i)) + ",");
        }
        saveDataString.append("]");
        return saveDataString;
    }
}
