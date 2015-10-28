package com.mazzotta.kuster.clickpoint.game.level;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.mazzotta.kuster.clickpoint.game.commands.CommandAction;
import com.mazzotta.kuster.clickpoint.game.commands.History;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mazzotta.kuster.clickpoint.game.level.FileOperationUtil.getSavePath;

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
