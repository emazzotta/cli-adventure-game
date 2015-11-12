package com.mazzotta.kuster.pointandclick.adventure.commands;

import com.mazzotta.kuster.pointandclick.adventure.commands.parsing.InputValidator;
import com.mazzotta.kuster.pointandclick.adventure.commands.parsing.exception.InvalidUserInputException;

import javax.swing.*;

public class CommandAction {

    private Command command;
    private ActionType actionType;
    private ActionIdentifier actionIdentifier;

    public CommandAction() {
        command = Command.NONE;
        actionType = ActionType.NONE;
        actionIdentifier = ActionIdentifier.NONE;
    }

    public CommandAction(String commandActionFragments[]) {
        command = Command.convertToCommand(commandActionFragments[0]);

        if(commandActionFragments.length >= 2) {
            actionType = ActionType.convertToActionType(commandActionFragments[1]);
        }
        else {
            actionType = ActionType.NONE;
        }
        if(commandActionFragments.length >= 3) {
            actionIdentifier = ActionIdentifier.convertToActionIdentifier(commandActionFragments[2]);
        }
        else {
            actionIdentifier = ActionIdentifier.NONE;
        }
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) throws InvalidUserInputException {
        InputValidator.validateCommand(command.name());
        this.command = command;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) throws InvalidUserInputException {
        InputValidator.validateActionType(actionType.name());
        this.actionType = actionType;
    }

    public ActionIdentifier getActionIdentifier() {
        return actionIdentifier;
    }

    public void setActionIdentifier(ActionIdentifier actionIdentifier) throws InvalidUserInputException {
        InputValidator.validateActionIdentifier(actionIdentifier.name());
        this.actionIdentifier = actionIdentifier;
    }
}
