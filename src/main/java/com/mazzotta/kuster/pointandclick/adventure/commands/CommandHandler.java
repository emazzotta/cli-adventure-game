package com.mazzotta.kuster.pointandclick.adventure.commands;


import com.mazzotta.kuster.pointandclick.adventure.game.elements.Room;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.UserState;

public class CommandHandler {

    CommandAction commandAction;

    public CommandHandler(CommandAction newCommandAction) {
        commandAction = newCommandAction;
    }

    public void executeCommand() {

        switch (commandAction.getCommand()) {
            case NONE:
                return;
            case OPEN:
               handleOpenCommand();
                return;
            case CLOSE:
                return;
            case SHOOT:
                return;
            case KILL:
                return;
            case WALK:
                return;
            case CROUCH:
                return;
            case COLLECT:
                return;
            case TAKE:
                return;
            case USE:
                return;
            case CONSUME:
                return;
            case FLEE:
                return;
            case FIGHT:
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
}
