package com.mazzotta.kuster.clickpoint.game.level;

import java.io.File;

public class Saver {

    public void saveAs(String filename) {
        String savePath = getSavePath(filename);

    }

    private String getSavePath(String filename) {
        String currentUsersHomeDir = System.getProperty("user.home");
        String saveFolder = currentUsersHomeDir + File.separator + "point_and_click";
        return saveFolder + File.separator + filename + ".json";
    }
}
