package com.mazzotta.kuster.pointandclick.adventure.util;

import java.io.File;

public class FileOperationUtil {
    public static File getSavegameFile(String filename) {
        return new File(getSavegameFolder() + File.separator + filename + ".json");
    }

    public static String getSavegameFolder() {
        return System.getProperty("user.home") + File.separator + "point_and_click";
    }

    public static String getPotentialSavegamesForFolder(String folder) {
        StringBuilder potentialSavegames = new StringBuilder();
        File[] listOfFiles = new File(folder).listFiles();

        if(listOfFiles != null) {
            for (File file : listOfFiles) {
                if (isAPossibleSavegame(file)) {
                    potentialSavegames.append(file.getName()).append("\n");
                }
            }
        }

        return potentialSavegames.toString();
    }

    private static boolean isAPossibleSavegame(File fileEntry) {
        return !fileEntry.isDirectory() && fileEntry.toString().endsWith(".json") && !fileEntry.toString().endsWith("/.json");
    }
}
