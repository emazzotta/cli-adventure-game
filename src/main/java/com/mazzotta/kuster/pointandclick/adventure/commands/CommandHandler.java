package com.mazzotta.kuster.pointandclick.adventure.commands;


import com.mazzotta.kuster.pointandclick.adventure.game.elements.UserState;
import com.mazzotta.kuster.pointandclick.adventure.level.Loader;
import com.mazzotta.kuster.pointandclick.adventure.level.Saver;
import com.mazzotta.kuster.pointandclick.adventure.main.Game;
import org.apache.commons.lang3.StringUtils;

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
                handleNoneCommand();
                return;
            case OPEN:
                handleOpenCommand();
                return;
            case COLLECT:
                handleCollectCommand();
                return;
            case USE:
                handleUseCommand();
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
            case HISTORY:
                handleHistoryCommand();
                return;
            case RESET:
                handleResetCommand();
        }
    }

    private void handleNoneCommand() {
        Queue.getInstance().addGameOutput("Either you entered an invalid command or you expect\nsomething magical to happen by entering \"NONE\"");
    }

    private void handleUseCommand() {
        switch (commandAction.getActionType()) {
            case POTION:
                handleUsePotionActionType();
                return;
            default:
                Queue.getInstance().addGameOutput("Use command is not possible with " + commandAction.getActionType());
        }
    }

    private void handleUsePotionActionType() {
        if (StringUtils.isNumeric(commandAction.getActionIdentifier().toString())) {
            int potionPosition = Integer.parseInt(commandAction.getActionIdentifier().toString());
            UserState.getInstance().getPlayer().drinkPotion(potionPosition);
        } else {
            Queue.getInstance().addGameOutput("Invalid Potion Position! Use command like: USE POTION 1");
        }
    }

    private void handleResetCommand() {
        UserState.getInstance().resetUserState();
        History.getInstance().clearCommands();
        Game.showInitialText = true;
    }

    private void handleHistoryCommand() {
        Queue.getInstance().addGameOutput("Entered Commands:\n" + History.getInstance().getEnteredCommands().toString());
    }

    private void handleHelpCommand() {
        Queue.getInstance().addGameOutput("The HELP-FAIRY was summoned! Here are some tips:\n" +
                "You can play this game typing commands. A command consists of a maximum of three parts.\n" +
                "This is a command: OPEN DOOR\n" +
                "Here are a list of commands arguments:\n\n" +
                "1st command arguments:\n" +
                Command.listAvailable() +
                "2nd command arguments:\n" +
                ActionType.listAvailable() +
                "3rd command arguments:\n" +
                "This is actually just a number. It will identify the exact action item you're referring to.\n" +
                "Though it will be a name when referring to saving and loading, see below:\n" +
                "To save your current game type: SAVE GAME 'save_filename'\n" +
                "To load a saved game type: LOAD GAME 'save_filename'\n" +
                "Now go ahead and beat the monsters!");
    }

    private void handleSaveCommand() {
        new Saver().saveAs(commandAction.getActionIdentifier().getIdentifierId());
    }

    private void handleLoadCommand() {
        new Loader().loadFromJsonFile(commandAction.getActionIdentifier().getIdentifierId());
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

    public void handleCollectCommand() {
        switch (commandAction.getActionType()) {
            case ITEMS:
                UserState.getInstance().getPlayer().addToInventory(UserState.getInstance().getCurrentRoom().getItems());
        }
    }

    public void handleFightCommand() {

    }
}
