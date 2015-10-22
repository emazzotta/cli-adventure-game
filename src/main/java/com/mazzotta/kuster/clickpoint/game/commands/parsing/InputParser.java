package com.mazzotta.kuster.clickpoint.game.commands.parsing;

import com.mazzotta.kuster.clickpoint.game.commands.CommandAction;

import java.util.Scanner;

public class InputParser {

    private CommandAction commandAction;

    public InputParser() {
    }

    public void createCommandActionFrom(String userInput) throws InvalidUserInputException {
        String commandActionFragments[] = userInput.split("\\s+");
        commandAction = new CommandAction(commandActionFragments);
    }

    public CommandAction getCommandAction() {
        return commandAction;
    }
}
