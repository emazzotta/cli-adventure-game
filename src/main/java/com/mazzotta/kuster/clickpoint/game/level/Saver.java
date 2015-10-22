package com.mazzotta.kuster.clickpoint.game.level;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mazzotta.kuster.clickpoint.game.commands.Command;
import com.mazzotta.kuster.clickpoint.game.commands.CommandAction;
import com.mazzotta.kuster.clickpoint.game.commands.History;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Saver {

    Gson gson;

    public Saver() {
        gson = new GsonBuilder().create();
    }

    public void saveAs(String filename) {
        String savePath = getSavePath(filename);
        File saveToSaveTo = new File(savePath);

        StringBuilder saveDataString = new StringBuilder("history: [");

        ArrayList<CommandAction> enteredCommands = History.getInstance().getEnteredCommands();
        for(int i=0;i<enteredCommands.size();i++) {
            saveDataString.append(gson.toJson(enteredCommands.get(i)) + ",");
        }

        saveDataString.append("]");

        try {
            FileUtils.write(saveToSaveTo, saveDataString.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(
          //      gson.fromJson("{\"command\":\"COLLECT\",\"actionType\":\"POTION\",\"actionIdentifier\":\"ALL\"}",
            //            CommandAction.class));
    }

    public String getSavePath(String filename) {
        String currentUsersHomeDir = System.getProperty("user.home");
        String saveFolder = currentUsersHomeDir + File.separator + "point_and_click";
        return saveFolder + File.separator + filename + ".json";
    }
}
