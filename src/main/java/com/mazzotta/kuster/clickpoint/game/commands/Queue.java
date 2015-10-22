package com.mazzotta.kuster.clickpoint.game.commands;

import java.util.ArrayList;

public class Queue {
    private static Queue instance;
    private static ArrayList<String> pendingUserInput;
    private static ArrayList<String> pendingGameOutput;

    public static Queue getInstance() {
        if(instance == null) {
            instance = new Queue();
        }
        return instance;
    }

    private Queue() {
        pendingUserInput = new ArrayList<String>();
        pendingGameOutput = new ArrayList<String>();
    }

    public void clearUserInputCache() {
        if(!pendingUserInput.isEmpty()) {
            pendingUserInput.clear();
        }
    }

    public void clearGameOutputCache() {
        if(!pendingGameOutput.isEmpty()) {
            pendingGameOutput.clear();
        }
    }

    public ArrayList<String> getPendingUserInput() {
        return pendingUserInput;
    }

    public ArrayList<String> getPendingGameOutput() {
        return pendingGameOutput;
    }

    public void addUserInput(String userInput) {
        pendingUserInput.add(userInput);
    }
    public void addGameOutput(String userInput) {
        pendingGameOutput.add(userInput);
    }
}
