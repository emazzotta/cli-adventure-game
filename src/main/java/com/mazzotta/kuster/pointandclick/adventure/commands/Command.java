package com.mazzotta.kuster.pointandclick.adventure.commands;

public enum Command {
    NONE, OPEN, COLLECT, ATTACK, INSPECT, USE, HELP, SAVE, LOAD, HISTORY, RESET;

    public static Command convertToCommand(String commandText) {
        for(Command command : Command.values()) {
            if(command.name().equals(commandText.toUpperCase())) {
                return command;
            }
        }
        return NONE;
    }

    public static boolean contains(String commandToLookFor) {
        for(Command command : Command.values()) {
            if(command.name().equals(commandToLookFor.toUpperCase())) {
                return true;
            }
        }
        return false;
    }
}
