package com.mazzotta.kuster.pointandclick.adventure.util;

import java.io.File;

public class FileOperationUtil {
    public static File getSaveGameFile(String filename) {
        return new File(getSaveGameFolder() + File.separator + filename + ".json");
    }

    public static String getSaveGameFolder() {
        String currentUsersHomeDir = System.getProperty("user.home");
        return currentUsersHomeDir + File.separator + "point_and_click";
    }

    public static String listFilesForFolder(final File folder) {
        StringBuilder potentialSaveGames = new StringBuilder();
        for (final File file : folder.listFiles()) {
            if (isAPossibleSaveGame(file)) {
                potentialSaveGames.append(file.getName() + "\n");
            }
        }

        return potentialSaveGames.toString();
    }

    private static boolean isAPossibleSaveGame(File fileEntry) {
        return !fileEntry.isDirectory() && fileEntry.toString().endsWith(".json") && !fileEntry.toString().endsWith("/.json");
    }
}
