package com.mazzotta.kuster.pointandclick.adventure.commands.parsing;

import com.mazzotta.kuster.pointandclick.adventure.commands.CommandAction;
import com.mazzotta.kuster.pointandclick.adventure.exceptions.InvalidUserInputException;

public class InputParser {

    public CommandAction getCommandActionFrom(String userInput) {
        String commandActionFragments[] = userInput.split("\\s+");
        try {
            InputValidator.validateCommandActionFragments(commandActionFragments);
        } catch (InvalidUserInputException e) {
            e.printStackTrace();
        }
        return new CommandAction(commandActionFragments);
    }
}
