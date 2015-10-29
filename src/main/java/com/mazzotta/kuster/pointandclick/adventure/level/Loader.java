package com.mazzotta.kuster.pointandclick.adventure.level;

import com.google.gson.*;
import com.mazzotta.kuster.pointandclick.adventure.commands.CommandAction;
import com.mazzotta.kuster.pointandclick.adventure.commands.History;
import org.apache.commons.io.FileUtils;

import java.io.IOException;

import static com.mazzotta.kuster.pointandclick.adventure.util.FileOperationUtil.getSavePath;

public class Loader {

    Gson gson;

    public Loader() {
        gson = new GsonBuilder().create();
    }

    public void loadFromJsonFile(String filename) {
        try {
            String loadedJson = FileUtils.readFileToString(getSavePath(filename));
            JsonArray commandsAsJson = getStringAsJsonArray(loadedJson);
            loadJsonArrayIntoHistory(commandsAsJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadJsonArrayIntoHistory(JsonArray commandsAsJson) {
        for(JsonElement commandAsJson: commandsAsJson) {
            CommandAction commandAction = gson.fromJson(commandAsJson , CommandAction.class);
            History.getInstance().addEnteredCommand(commandAction);
        }
    }

    private JsonArray getStringAsJsonArray(String loadedJson) {
        return new JsonParser().parse(loadedJson).getAsJsonArray();
    }
}
