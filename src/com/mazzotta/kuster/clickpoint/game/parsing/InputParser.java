package com.mazzotta.kuster.clickpoint.game.parsing;

import com.mazzotta.kuster.clickpoint.game.commands.ActionIdentifier;
import com.mazzotta.kuster.clickpoint.game.commands.ActionType;
import com.mazzotta.kuster.clickpoint.game.commands.Command;
import com.mazzotta.kuster.clickpoint.game.commands.CommandAction;

import java.util.Scanner;

public class InputParser {

    private String messageToAskUser;
    private CommandAction commandAction;

    public InputParser(String messageToAskUser) {
        this.messageToAskUser = messageToAskUser;
        this.commandAction = new CommandAction(Command.NONE, ActionType.NONE, ActionIdentifier.NONE);
    }

    public void askUser() throws InvalidUserInputException {
        String userInput = getUserInput();
        createCommandActionFrom(userInput);
    }

    private void createCommandActionFrom(String userInput) throws InvalidUserInputException {
        String commandActionFragments[] = userInput.split("\\s+");
        commandAction = new CommandAction(commandActionFragments);
    }

    private String getUserInput() {
        System.out.print(messageToAskUser + ": ");
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    public CommandAction getCommandAction() {
        return commandAction;
    }

    public static void main(String args[]) {
        try {
            InputParser inputParser = new InputParser("Type a command");
            inputParser.askUser();
            System.out.println("Successfully created an object");

            CommandAction commandAction = inputParser.getCommandAction();

            System.out.println("Command: " + commandAction.getCommand());
            System.out.println("Action Type: " + commandAction.getActionType());
            System.out.println("Action Identifier: " + commandAction.getActionIdentifier());
        } catch (InvalidUserInputException e) {
            System.out.println(e.getMessage());
        }

        ;
    }
}
