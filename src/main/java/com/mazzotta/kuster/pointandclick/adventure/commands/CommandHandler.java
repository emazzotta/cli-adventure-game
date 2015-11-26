package com.mazzotta.kuster.pointandclick.adventure.commands;


import com.mazzotta.kuster.pointandclick.adventure.game.elements.UserState;
import com.mazzotta.kuster.pointandclick.adventure.level.Initializer;
import com.mazzotta.kuster.pointandclick.adventure.level.Loader;
import com.mazzotta.kuster.pointandclick.adventure.level.Saver;
import org.apache.commons.lang3.StringUtils;

public class CommandHandler {

    CommandAction commandAction;

    public CommandHandler(CommandAction newCommandAction) {
        commandAction = newCommandAction;
    }

    public void executeCommand() {

        switch(commandAction.getCommand()) {
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
            case ATTACK:
                handleAttackCommand();
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
        Queue.getInstance().addGameOutput("I'm sorry, but I'm afraid I can't handle invalid commands, Dave.");
    }

    private void handleUseCommand() {
        switch(commandAction.getActionType()) {
            case POTION:
                handleUsePotionActionType();
                return;
            default:
                Queue.getInstance().addGameOutput("Use command is not possible with " + commandAction.getActionType());
        }
    }

    private void handleUsePotionActionType() {
        if(StringUtils.isNumeric(commandAction.getActionIdentifier().toString())) {
            int potionPosition = Integer.parseInt(commandAction.getActionIdentifier().toString());
            UserState.getInstance().getPlayer().drinkPotion(potionPosition);
        } else {
            Queue.getInstance().addGameOutput("Invalid Potion Position! Use command like: USE POTION 1");
        }
    }

    private void handleResetCommand() {
        Initializer.getInstance().initialise();
    }

    private void handleHistoryCommand() {
        Queue.getInstance().addGameOutput("Entered Commands:\n" + History.getInstance().getEnteredCommands().toString());
    }

    private void handleHelpCommand() {
        Queue.getInstance().addGameOutput("The HELP-FAIRY was summoned! Here are some tips:\n" +
                "You can play this game typing commands. A command consists of a maximum of three parts.\n" +
                "\nThis is a list of useful commands:\n" +
                "OPEN DOOR - Go to the next room\n" +
                "COLLECT ITEMS - Collect items in current room\n" +
                "INSPECT ROOM - Check for items in current room\n" +
                "INSPECT INVENTORY - Check items in inventory\n" +
                "USE POTION 1 - Use potion at position one (use command above to check which potion is at which position)\n" +
                "ATTACK - Attack the monster in the current room\n" +
                "RESET - Restart the game\n" +
                "HISTORY - Show history of typed commands\n" +
                "SAVE GAME 'save_filename' - Save the game\n" +
                "SAVE - Quick save, the name of the file will be empty\n" +
                "LOAD GAME 'save_filename' - Load the game\n" +
                "LOAD - Load the quick save\n" +
                "HELP - Show this help\n\n" +
                "Now go ahead and beat the monsters!");
    }

    private void handleSaveCommand() {
        new Saver().saveAs(commandAction.getActionIdentifier().getIdentifierId());
    }

    private void handleLoadCommand() {
        new Loader().loadFromJsonFile(commandAction.getActionIdentifier().getIdentifierId());
    }

    public void handleOpenCommand() {
        switch(commandAction.getActionType()) {
            case DOOR:
                UserState.getInstance().changeRoom();
                return;
            default:
                Queue.getInstance().addGameOutput("Invalid Open Action Type, try OPEN DOOR");
        }
    }

    public void handleInspectCommand() {
        switch(commandAction.getActionType()) {
            case INVENTORY:
                UserState.getInstance().getPlayer().getInventory().showInventory();
                return;
            case ROOM:
                UserState.getInstance().getCurrentRoom().showRoomContent();
                return;
            default:
                Queue.getInstance().addGameOutput("Invalid Inspect Action Type, try INSPECT ROOM or INSPECT INVENTORY");
        }
    }

    public void handleCollectCommand() {
        switch(commandAction.getActionType()) {
            case ITEMS:
                UserState.getInstance().getPlayer().addToInventory(UserState.getInstance().getCurrentRoom().getItems());
                return;
            default:
                Queue.getInstance().addGameOutput("Invalid Collect Action Type, try COLLECT ITEMS");
        }
    }

    public void handleAttackCommand() {
        UserState.getInstance().attackMonster();
    }
}
