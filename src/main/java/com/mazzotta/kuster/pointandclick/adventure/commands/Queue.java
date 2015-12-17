package com.mazzotta.kuster.pointandclick.adventure.commands;

import java.util.ArrayList;

public class Queue {
    private static Queue instance;
    private static ArrayList<CommandAction> pendingUserInput;
    private static ArrayList<String> pendingGameOutput;

    public static Queue getInstance() {
        if(instance == null) {
            instance = new Queue();
        }
        return instance;
    }

    private Queue() {
        pendingUserInput = new ArrayList<>();
        pendingGameOutput = new ArrayList<>();
    }

    public void clearGameOutputCache() {
        pendingGameOutput.clear();
    }

    public ArrayList<CommandAction> getPendingUserInput() {
        return pendingUserInput;
    }

    public ArrayList<String> getPendingGameOutput() {
        return pendingGameOutput;
    }

    public void addUserInput(CommandAction userInput) {
        pendingUserInput.add(userInput);
    }

    public void addGameOutput(String gameOutput) {
        pendingGameOutput.add(gameOutput);
    }
}
