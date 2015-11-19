package com.mazzotta.kuster.pointandclick.adventure.commands;


import com.mazzotta.kuster.pointandclick.adventure.game.elements.UserState;

public class CommandHandler {

    CommandAction commandAction;

    public CommandHandler(CommandAction newCommandAction) {
        commandAction = newCommandAction;
    }

    public void executeCommand() {

        switch (commandAction.getCommand()) {
            case NONE:
                System.out.println("[Command = NONE] No Command available!");
                return;
            case OPEN:
                System.out.println("[Command = OPEN] Execute [OPEN] command");
               handleOpenCommand();
                return;
            case COLLECT:
                return;
            case USE:
                return;
            case FIGHT:
                return;
            case INSPECT:
                System.out.println("[Command = INSPECT] Execute [INSPECT] command");
                handleInspectCommand();
                return;
        }


        System.out.println("Command executed(teststatement)");


        //TODO implement execution of the command. Also map available commands to actions.
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
