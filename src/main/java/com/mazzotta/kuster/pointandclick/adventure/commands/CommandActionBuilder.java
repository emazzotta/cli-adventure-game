package com.mazzotta.kuster.pointandclick.adventure.commands;

public class CommandActionBuilder {

    private Command command;
    private ActionType actionType;
    private ActionIdentifier actionIdentifier;

    public CommandActionBuilder setCommand(Command command) {
        this.command = command;
        return this;
    }

    public CommandActionBuilder setCommand(String command) {
        this.command = Command.convertToCommand(command);
        return this;
    }

    public CommandActionBuilder setActionType(ActionType actionType) {
        this.actionType = actionType;
        return this;
    }

    public CommandActionBuilder setActionType(String actionType) {
        this.actionType = ActionType.convertToActionType(actionType);
        return this;
    }

    public CommandActionBuilder setActionIdentifier(ActionIdentifier actionIdentifier) {
        this.actionIdentifier = actionIdentifier;
        return this;
    }

    public CommandActionBuilder setActionIdentifier(String actionIdentifier) {
        this.actionIdentifier = new ActionIdentifier(actionIdentifier);
        return this;
    }

    public CommandAction build() {
        command = command == null ? Command.NONE : command;
        actionType = actionType == null ? ActionType.NONE : actionType;
        actionIdentifier = actionIdentifier == null ? new ActionIdentifier(""): actionIdentifier;
        return new CommandAction(command, actionType, actionIdentifier);
    }
}
