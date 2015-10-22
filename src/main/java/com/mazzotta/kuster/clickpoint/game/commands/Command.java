package com.mazzotta.kuster.clickpoint.game.commands;

import java.util.ArrayList;

public enum Command {
    NONE, OPEN, CLOSE, SHOOT, KILL, WALK, CROUCH, COLLECT, TAKE;

    public static Command convertToCommand(String commandText) {
        for (Command command : Command.values()) {
            if (command.name().equals(commandText.toUpperCase())) {
                return command;
            }
        }
        return NONE;
    }

    public static boolean contains(String commandToLookFor) {
        for (Command command : Command.values()) {
            if (command.name().equals(commandToLookFor.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<String> thatAreAvailable() {
        ArrayList<String> availableCommands = new ArrayList<String>();
        for (Command command : Command.values()) {
            availableCommands.add(command.name());
        }
        return availableCommands;
    }
}
