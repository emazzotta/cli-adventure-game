package com.mazzotta.kuster.pointandclick.adventure.commands;

import com.mazzotta.kuster.pointandclick.adventure.game.elements.State;

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
        pendingUserInput = new ArrayList<>();
        pendingGameOutput = new ArrayList<>();
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
                "Health: " + State.getInstance().getPlayer().getHealth() + "\n" +
                "Attack Strength: " + State.getInstance().getPlayer().getAttackPoints() + "\n" +
                "Current Weapon: " + State.getInstance().getPlayer().getEquippedWeapon().getName() + "\n" +
                "Amount of Potions: " + State.getInstance().getPlayer().getInventory().getPotions().size() + "\n" +
                "Current Room: " + State.getInstance().getCurrentRoom().getName() + "\n" +
                "###############################\n\n";
    }
}
