package com.mazzotta.kuster.clickpoint.game.commands;

import java.util.ArrayList;

public class History {
    private static History instance;
    private static ArrayList<CommandAction> enteredCommands;

    public static History getInstance() {
        if(instance == null) {
            instance = new History();
        }
        return instance;
    }

    private History() {
        enteredCommands = new ArrayList<CommandAction>();
    }

    public ArrayList<CommandAction> getEnteredCommands() {
        return enteredCommands;
    }

    public void addEnteredCommand(CommandAction commandAction) {
        enteredCommands.add(commandAction);
    }
}
