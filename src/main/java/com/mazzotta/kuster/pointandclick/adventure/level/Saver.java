package com.mazzotta.kuster.pointandclick.adventure.level;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mazzotta.kuster.pointandclick.adventure.commands.History;
import com.mazzotta.kuster.pointandclick.adventure.util.FileOperationUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Saver {

    Gson gson;

    public Saver() {
        gson = new GsonBuilder().create();
    }

    public void saveAs(String filename) {
        File savePath = FileOperationUtil.getSavePath(filename);
        String saveDataString = gson.toJson(History.getInstance().getEnteredCommands());

        try {
            FileUtils.write(savePath, saveDataString);
        } catch (IOException e) {
            // TODO Something went wrong --> add to queue
        }
    }
}
