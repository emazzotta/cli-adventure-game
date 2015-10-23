package com.mazzotta.kuster.clickpoint.game.level;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mazzotta.kuster.clickpoint.game.commands.History;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

import static com.mazzotta.kuster.clickpoint.game.level.FileOperationUtil.getSavePath;

public class Saver {

    Gson gson;

    public Saver() {
        gson = new GsonBuilder().create();
    }

    public void saveAs(String filename) {
        File savePath = getSavePath(filename);
        String saveDataString = buildJsonFromHistory();

        try {
            FileUtils.write(savePath, saveDataString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String buildJsonFromHistory() {
        JsonObject enteredCommandsJson = new JsonObject();
        enteredCommandsJson.addProperty("History", gson.toJson(History.getInstance().getEnteredCommands()));
        return enteredCommandsJson.toString();
    }
}
