package com.mazzotta.kuster.pointandclick.adventure.level;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mazzotta.kuster.pointandclick.adventure.commands.History;
import com.mazzotta.kuster.pointandclick.adventure.commands.Queue;
import com.mazzotta.kuster.pointandclick.adventure.util.FileOperationUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Saver {

    private Gson gson;

    public Saver() {
        gson = new GsonBuilder().create();
    }

    public void saveAs(String filename) {
        filename = filename.equals("") ? "quicksave" : filename;
        File savePath = FileOperationUtil.getSavegameFile(filename);
        String saveDataString = gson.toJson(History.getInstance().getEnteredCommands());

        try {
            FileUtils.write(savePath, saveDataString);
            Queue.getInstance().addGameOutput("Game saved successfully! Savegame: " + savePath);
        } catch (IOException e) {
            Queue.getInstance().addGameOutput("Error saving to file " + filename + "\n" + e.getMessage());
        }
    }
}
