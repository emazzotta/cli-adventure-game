package com.mazzotta.kuster.pointandclick.adventure.commands;


import com.mazzotta.kuster.pointandclick.adventure.game.elements.UserState;

public class CommandHandler {

    CommandAction commandAction;

    public CommandHandler(CommandAction newCommandAction) {
        commandAction = newCommandAction;
    }

    public void executeCommand() {

        switch (commandAction.getCommand()) {
            default:
                System.out.println("Executing [Command = " + commandAction.getCommand() + "]");
            case NONE:
                return;
            case OPEN:
               handleOpenCommand();
                return;
            case COLLECT:
                return;
            case USE:
                return;
            case FIGHT:
                return;
            case INSPECT:
                handleInspectCommand();
                return;
            case HELP:
                handleHelpCommand();
                return;
            case SAVE:
                handleSaveCommand();
                return;
            case LOAD:
                handleLoadCommand();
                return;
        }

        //TODO implement execution of the command. Also map available commands to actions.
    }

    private void handleHelpCommand() {

    }

    private void handleSaveCommand() {

    }

    private void handleLoadCommand() {

    }

    public void handleOpenCommand() {
        switch (commandAction.getActionType()) {
            case DOOR:
                UserState.getInstance().changeRoom();
                break;
        }
    }

    public void handleInspectCommand() {
        switch (commandAction.getActionType()) {
            case INVENTORY:
                UserState.getInstance().getPlayer().getInventory().showInventory();
                break;
            case ROOM:
                UserState.getInstance().getCurrentRoom().showRoomContent();
                break;
        }
    }
}
