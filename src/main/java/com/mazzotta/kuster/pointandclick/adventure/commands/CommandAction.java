package com.mazzotta.kuster.pointandclick.adventure.commands;

import java.util.ArrayList;

public class CommandAction {

    private Command command;
    private ActionType actionType;
    private ActionIdentifier actionIdentifier;

    public CommandAction(ArrayList<String> commandActionFragments) {
        commandActionFragments.add("");
        commandActionFragments.add("");
        commandActionFragments.add("");
        command = Command.convertToCommand(commandActionFragments.get(0));
        actionType = ActionType.convertToActionType(commandActionFragments.get(1));
        actionIdentifier = new ActionIdentifier(commandActionFragments.get(2));
    }

    public CommandAction(Command command, ActionType actionType, ActionIdentifier actionIdentifier) {
        this.command = command;
        this.actionType = actionType;
        this.actionIdentifier = actionIdentifier;
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
        String commandAction = command == Command.NONE ? "" : "" + command;
        commandAction += actionType == ActionType.NONE ? "" : " " + actionType;
        commandAction += actionIdentifier.toString().equals("") ? "" : " " + actionIdentifier;
        return commandAction;
    }
}
