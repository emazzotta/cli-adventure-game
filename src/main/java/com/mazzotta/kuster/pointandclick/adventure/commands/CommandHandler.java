package com.mazzotta.kuster.pointandclick.adventure.commands;


import com.mazzotta.kuster.pointandclick.adventure.game.elements.UserState;
import com.mazzotta.kuster.pointandclick.adventure.main.Game;

public class CommandHandler {

    CommandAction commandAction;

    public CommandHandler(CommandAction newCommandAction) {
        commandAction = newCommandAction;
    }

    public void executeCommand() {

        switch (commandAction.getCommand()) {
            default:
                System.out.println("Executing [Command = " + commandAction.getCommand() + "]");
            case NONE:
                return;
            case OPEN:
               handleOpenCommand();
                return;
            case COLLECT:
                return;
            case USE:
                return;
            case FIGHT:
                handleFightCommand();
                return;
            case INSPECT:
                handleInspectCommand();
                return;
            case HELP:
                handleHelpCommand();
                return;
            case SAVE:
                handleSaveCommand();
                return;
            case LOAD:
                handleLoadCommand();
                return;
        }

        //TODO implement execution of the command. Also map available commands to actions.
    }

    private void handleHelpCommand() {
        Queue.getInstance().addGameOutput("The HELP-FAIRY was summoned! Here are some tips:\n" +
                "You can play this game typing commands. A command consists of a maximum of three parts.\n" +
                "This is a command: OPEN DOOR\n" +
                "Here are a list of commands arguments:\n\n" +
                "1st command arguments:\n" +
                Command.listAvailable() +
                "2st command arguments:\n" +
                ActionType.listAvailable() +
                "3st command arguments:\n" +
                "This is actually just a number. It will identify the exact action item you're referring to.\n\n" +
                "To save your current game type: SAVE GAME 'save_filename'\n" +
                "To load a saved game type: LOAD GAME 'save_filename'\n" +
                "Now go ahead and beat the monsters!");
    }

    private void handleSaveCommand() {
        Game.getInstance().getSaver().saveAs(commandAction.getActionIdentifier().getIdentifierId());
    }

    private void handleLoadCommand() {

    }

    public void handleOpenCommand() {
        switch (commandAction.getActionType()) {
            case DOOR:
                UserState.getInstance().changeRoom();
                break;
        }
    }

    public void handleInspectCommand() {
        switch (commandAction.getActionType()) {
            case INVENTORY:
                UserState.getInstance().getPlayer().getInventory().showInventory();
                break;
            case ROOM:
                UserState.getInstance().getCurrentRoom().showRoomContent();
                break;
        }
    }

    public void handleFightCommand() {

    }
}
