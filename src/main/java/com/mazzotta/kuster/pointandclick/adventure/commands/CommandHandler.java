package com.mazzotta.kuster.pointandclick.adventure.commands;


import com.mazzotta.kuster.pointandclick.adventure.game.elements.State;
import com.mazzotta.kuster.pointandclick.adventure.level.Initializer;
import com.mazzotta.kuster.pointandclick.adventure.level.Loader;
import com.mazzotta.kuster.pointandclick.adventure.level.Saver;
import org.apache.commons.lang3.StringUtils;

public class CommandHandler {

    public static void execute(CommandAction commandAction) {
        History.getInstance().addEnteredCommand(commandAction);
        switch(commandAction.getCommand()) {
            case OPEN:
                handleOpenCommand(commandAction);
                return;
            case COLLECT:
                handleCollectCommand(commandAction);
                return;
            case USE:
                handleUseCommand(commandAction);
                return;
            case INSPECT:
                handleInspectCommand(commandAction);
                return;
            case SAVE:
                handleSaveCommand(commandAction);
                return;
            case LOAD:
                handleLoadCommand(commandAction);
                return;
            case ATTACK:
                handleAttackCommand();
                return;
            case HELP:
                handleHelpCommand();
                return;
            case HISTORY:
                handleHistoryCommand();
                return;
            case RESET:
                handleResetCommand();
                return;
            case NONE:
                handleNoneCommand();
        }
    }

    private static void handleOpenCommand(CommandAction commandAction) {
        switch(commandAction.getActionType()) {
            case DOOR:
                State.getInstance().changeRoom();
                return;
            default:
                Queue.getInstance().addGameOutput("Invalid Open Action Type, try OPEN DOOR");
        }
    }

    private static void handleCollectCommand(CommandAction commandAction) {
        switch(commandAction.getActionType()) {
            case ITEMS:
                State.getInstance().getPlayer().addToInventory(State.getInstance().getCurrentRoom().getItems());
                State.getInstance().getCurrentRoom().removeAllItems();
                return;
            default:
                Queue.getInstance().addGameOutput("Invalid Collect Action Type, try COLLECT ITEMS");
        }
    }

    private static void handleUseCommand(CommandAction commandAction) {
        switch(commandAction.getActionType()) {
            case POTION:
                handleUsePotionActionType(commandAction);
                return;
            case WEAPON:
                handleUseWeaponActionType(commandAction);
                return;
            default:
                Queue.getInstance().addGameOutput("Use command is not possible with " + commandAction.getActionType());
        }
    }

    private static void handleInspectCommand(CommandAction commandAction) {
        switch(commandAction.getActionType()) {
            case INVENTORY:
                State.getInstance().getPlayer().getInventory().showInventory();
                return;
            case ROOM:
                String roomContent = State.getInstance().getCurrentRoom().getRoomContent();
                Queue.getInstance().addGameOutput(roomContent);
                return;
            default:
                Queue.getInstance().addGameOutput("Invalid Inspect Action Type, try INSPECT ROOM or INSPECT INVENTORY");
        }
    }

    private static void handleSaveCommand(CommandAction commandAction) {
        new Saver().saveAs(commandAction.getActionIdentifier().getIdentifierId());
    }

    private static void handleLoadCommand(CommandAction commandAction) {
        new Loader().loadFromJsonFile(commandAction.getActionIdentifier().getIdentifierId());
    }

    private static void handleAttackCommand() {
        State.getInstance().attackMonster();
    }

    private static void handleHelpCommand() {
        Queue.getInstance().addGameOutput("The HELP-FAIRY was summoned! Here are some tips:\n" +
                "You can play this game typing commands. A command consists of a maximum of three parts.\n" +
                "\nThis is a list of useful commands:\n" +
                "OPEN DOOR - Go to the next room\n" +
                "COLLECT ITEMS - Collect items in current room\n" +
                "INSPECT ROOM - Check for items in current room\n" +
                "INSPECT INVENTORY - Check items in inventory\n" +
                "USE POTION 1 - Use potion at position one (use command above to check which potion is at which position)\n" +
                "USE WEAPON 1 - Equip weapon at position one\n" +
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

    private static void handleHistoryCommand() {
        Queue.getInstance().addGameOutput("Entered Commands:\n" + History.getInstance().getEnteredCommands().toString());
    }

    private static void handleResetCommand() {
        Initializer.getInstance().initialise();
    }

    private static void handleNoneCommand() {
        Queue.getInstance().addGameOutput("I'm sorry, but I'm afraid I can't handle invalid commands, Dave.");
    }

    private static void handleUsePotionActionType(CommandAction commandAction) {
        if(StringUtils.isNumeric(commandAction.getActionIdentifier().toString())) {
            int potionPosition = Integer.parseInt(commandAction.getActionIdentifier().toString());
            State.getInstance().getPlayer().drinkPotionAtPosition(potionPosition);
        } else {
            Queue.getInstance().addGameOutput("Invalid Potion Position! Use command like: USE POTION 1");
        }
    }

    private static void handleUseWeaponActionType(CommandAction commandAction) {
        if(StringUtils.isNumeric(commandAction.getActionIdentifier().toString())) {
            int potionPosition = Integer.parseInt(commandAction.getActionIdentifier().toString());
            State.getInstance().getPlayer().setEquippedWeaponToWeaponAtPosition(potionPosition);
        } else {
            Queue.getInstance().addGameOutput("Invalid Weapon Position! Use command like: USE WEAPON 1");
        }
    }
}
