package com.mazzotta.kuster.pointandclick.adventure.commands.parsing;

import com.mazzotta.kuster.pointandclick.adventure.commands.ActionType;
import com.mazzotta.kuster.pointandclick.adventure.commands.Command;
import com.mazzotta.kuster.pointandclick.adventure.exceptions.InvalidUserInputException;

import java.util.ArrayList;

public class InputValidator {

    public static void validateCommandActionFragments(ArrayList<String> commandActionFragments) throws InvalidUserInputException {
        validateLength(commandActionFragments);
        validateCommand(commandActionFragments.get(0));
        if (commandActionFragments.size() > 1) {
            validateActionType(commandActionFragments.get(1));
        }
    }

    public static void validateLength(ArrayList<String> commandActionFragments) throws InvalidUserInputException {
        if (commandActionFragments.size() < 1) {
            throw new InvalidUserInputException("Try entering commands in this pattern: SHOOT ENEMY SLIMEMONSTER");
        }
    }

    public static void validateCommand(String command) throws InvalidUserInputException {
        command = command.toUpperCase();
        if (!Command.contains(command)) {
            throw new InvalidUserInputException("The command [" + command + "] does not exist");
        }
    }

    public static void validateActionType(String actionType) throws InvalidUserInputException {
        actionType = actionType.toUpperCase();
        if (!ActionType.contains(actionType)) {
            throw new InvalidUserInputException("The action type [" + actionType + "] does not exist");
        }
    }
}
