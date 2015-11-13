package com.mazzotta.kuster.pointandclick.adventure.commands;

import com.mazzotta.kuster.pointandclick.adventure.main.Game;

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
        pendingUserInput = new ArrayList<CommandAction>();
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

    public ArrayList<CommandAction> getPendingUserInput() {
        return pendingUserInput;
    }

    public ArrayList<String> getPendingGameOutput() {
        return pendingGameOutput;
    }

    public void addUserInput(CommandAction userInput) {
        Game.getInstance().running = false;
            pendingUserInput.add(userInput);
        Game.getInstance().running = true;
    }
    public void addGameOutput(String gameOutput) {
        pendingGameOutput.add(gameOutput);
    }
}
