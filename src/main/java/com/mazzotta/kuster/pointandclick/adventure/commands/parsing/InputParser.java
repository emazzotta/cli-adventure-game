package com.mazzotta.kuster.pointandclick.adventure.commands.parsing;

import com.mazzotta.kuster.pointandclick.adventure.commands.CommandAction;
import com.mazzotta.kuster.pointandclick.adventure.exceptions.InvalidUserInputException;

import java.util.ArrayList;
import java.util.Arrays;

public class InputParser {

    public CommandAction getCommandActionFrom(String userInput) {
        ArrayList<String> commandActionFragments = new ArrayList<>(Arrays.asList(userInput.split("\\s+")));
        try {
            InputValidator.validateCommandActionFragments(commandActionFragments);
        } catch (InvalidUserInputException e) {
            e.printStackTrace();
        }
        return new CommandAction(commandActionFragments);
    }
}
