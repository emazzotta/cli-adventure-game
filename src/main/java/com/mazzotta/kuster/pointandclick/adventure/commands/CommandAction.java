package com.mazzotta.kuster.pointandclick.adventure.commands;

public class CommandAction {

    private Command command;
    private ActionType actionType;
    private ActionIdentifier actionIdentifier;

    public CommandAction(String commandActionFragments[]) {
        command = Command.convertToCommand(commandActionFragments[0]);

        if(commandActionFragments.length >= 2) {
            actionType = ActionType.convertToActionType(commandActionFragments[1]);
        } else {
            actionType = ActionType.NONE;
        }
        if(commandActionFragments.length >= 3) {
            actionIdentifier = new ActionIdentifier(commandActionFragments[2]);
        } else {
            actionIdentifier = new ActionIdentifier("");
        }
    }

    public Command getCommand() {
        return command;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public ActionIdentifier getActionIdentifier() {
        return actionIdentifier;
    }

    public String toString() {
        String commandAction = command == Command.NONE ? "" : command + " ";
        commandAction += actionType == ActionType.NONE ? "" : actionType + " ";
        commandAction += actionIdentifier;
        return commandAction;
    }
}
