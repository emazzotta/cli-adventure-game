package com.mazzotta.kuster.pointandclick.adventure.commands.parsing;

import com.mazzotta.kuster.pointandclick.adventure.commands.ActionIdentifier;
import com.mazzotta.kuster.pointandclick.adventure.commands.ActionType;
import com.mazzotta.kuster.pointandclick.adventure.commands.Command;
import com.mazzotta.kuster.pointandclick.adventure.commands.parsing.exception.InvalidUserInputException;

public class InputValidator {

    public static void validateCommandActionFragments(String commandActionFragments[]) throws InvalidUserInputException {
        validateLength(commandActionFragments);
        validateCommand(commandActionFragments[0]);
        validateActionType(commandActionFragments[1]);
        validateActionIdentifier(commandActionFragments[2]);
    }

    public static void validateLength(String[] commandActionFragments) throws InvalidUserInputException {
        if(commandActionFragments.length != 3) {
            throw new InvalidUserInputException("Try entering commands in this pattern: SHOOT ENEMY SLIMEMONSTER");
        }
    }

    public static void validateCommand(String command) throws InvalidUserInputException {
        command = command.toUpperCase();
        if(!Command.contains(command)) {
            throw new InvalidUserInputException("The command [" + command + "] does not exist");
        }
    }

    public static void validateActionType(String actionType) throws InvalidUserInputException {
        actionType = actionType.toUpperCase();
        if(!ActionType.contains(actionType)) {
            throw new InvalidUserInputException("The action type [" + actionType + "] does not exist");
        }
    }

    public static void validateActionIdentifier(String actionIdentifier) throws InvalidUserInputException {
        actionIdentifier = actionIdentifier.toUpperCase();
        if(!ActionIdentifier.contains(actionIdentifier)) {
            throw new InvalidUserInputException("The action identifier [" + actionIdentifier + "] does not exist");
        }
    }
}
