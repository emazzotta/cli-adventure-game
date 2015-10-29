package com.mazzotta.kuster.pointandclick.adventure.commands.parsing;

import com.mazzotta.kuster.pointandclick.adventure.commands.CommandAction;

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
