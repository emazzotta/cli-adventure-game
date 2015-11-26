package com.mazzotta.kuster.pointandclick.adventure.commands;

import com.mazzotta.kuster.pointandclick.adventure.game.elements.UserState;

import java.util.ArrayList;

public class Queue {
    private static Queue instance;
    private static ArrayList<CommandAction> pendingUserInput;
    private static ArrayList<String> pendingGameOutput;

    public static Queue getInstance() {
        if(instance == null) {
            instance = new Queue();
        }
        return instance;
    }

    private Queue() {
        pendingUserInput = new ArrayList<CommandAction>();
        pendingGameOutput = new ArrayList<String>();
    }

    public void clearUserInputCache() {
        if(!pendingUserInput.isEmpty()) {
            pendingUserInput.clear();
        }
    }

    public void clearGameOutputCache() {
        if(!pendingGameOutput.isEmpty()) {
            pendingGameOutput.clear();
        }
    }

    public ArrayList<CommandAction> getPendingUserInput() {
        return pendingUserInput;
    }

    public ArrayList<String> getPendingGameOutput() {
        return pendingGameOutput;
    }

    public void addUserInput(CommandAction userInput) {
        pendingUserInput.add(userInput);
    }
    public void addGameOutput(String gameOutput) {
        System.out.println(gameOutput);
        pendingGameOutput.add(satistics() + gameOutput);
    }

    private String satistics() {
        return "###############################\n" +
                "Statistics:\n" +
                "Health: " + UserState.getInstance().getPlayer().getHealth() + "\n" +
                "Attack Strength: " + UserState.getInstance().getPlayer().getAttackPoints() + "\n" +
                "Current Weapon: " + UserState.getInstance().getPlayer().getEquippedWeapon().getName() + "\n" +
                "Amount of Potions: " + UserState.getInstance().getPlayer().getInventory().getPotions().size() + "\n" +
                "Current Room: " + UserState.getInstance().getCurrentRoom().getName() + "\n" +
                "###############################\n\n";
    }
}
