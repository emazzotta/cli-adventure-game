package com.mazzotta.kuster.clickpoint.game.commands;

import java.util.ArrayList;

public class Queue {
    private static Queue instance;
    private static ArrayList<String> outStandingUserInput;
    private static ArrayList<String> outStandingGameOutput;

    public static Queue getInstance() {
        if(instance == null) {
            instance = new Queue();
        }
        return instance;
    }

    private Queue() {
        outStandingUserInput = new ArrayList<String>();
        outStandingGameOutput = new ArrayList<String>();
    }

    public ArrayList<String> getOutStandingUserInput() {
        return outStandingUserInput;
    }

    public ArrayList<String> getOutStandingGameOutput() {
        return outStandingGameOutput;
    }

    public void addUserInput(String userInput) {
        outStandingUserInput.add(userInput);
    }
    public void addGameOutput(String userInput) {
        outStandingUserInput.add(userInput);
    }
}
