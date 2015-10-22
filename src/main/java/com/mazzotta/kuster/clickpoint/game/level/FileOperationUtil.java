package com.mazzotta.kuster.clickpoint.game.level;

import java.io.File;

public class FileOperationUtil {
    public static File getSavePath(String filename) {
        String currentUsersHomeDir = System.getProperty("user.home");
        String saveFolder = currentUsersHomeDir + File.separator + "point_and_click";
        return new File(saveFolder + File.separator + filename + ".json");
    }
}
