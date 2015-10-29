package com.mazzotta.kuster.pointandclick.adventure.main;

import com.mazzotta.kuster.pointandclick.adventure.commands.CommandAction;
import com.mazzotta.kuster.pointandclick.adventure.commands.History;
import com.mazzotta.kuster.pointandclick.adventure.commands.Queue;
import com.mazzotta.kuster.pointandclick.adventure.commands.parsing.InputParser;
import com.mazzotta.kuster.pointandclick.adventure.commands.parsing.exception.InvalidUserInputException;
import com.mazzotta.kuster.pointandclick.adventure.level.Initialiser;

import java.util.ArrayList;

public class Game {

    private static GUI gui;
    private static InputParser inputParser;
    private static Thread checkInput;
    private static int currentQueueSize;
    private static boolean running;

    private Game() {
        inputParser = new InputParser();
        running = false;
        currentQueueSize = Queue.getInstance().getPendingUserInput().size();
        checkInput = getThread();
        gui = new GUI();
        Initialiser.getInstance().initialise();
    }

    private Thread getThread() {
        return new Thread() {
            public void run() {
                running = true;
                while (running) {
                    try {
                        Thread.sleep(100);
                        if (Queue.getInstance().getPendingUserInput().size() > currentQueueSize) {
                            System.out.println("Input: " + Queue.getInstance().getPendingUserInput());
                            currentQueueSize = Queue.getInstance().getPendingUserInput().size();
                            Game.newQueueItemAvailable();
                            System.out.println("Output: " + Queue.getInstance().getPendingGameOutput());
                            Queue.getInstance().clearUserInputCache();
                            Queue.getInstance().clearGameOutputCache();
                        }
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        };
    }

    public static void newQueueItemAvailable() {
        ArrayList userInputs = Queue.getInstance().getPendingUserInput();
        InputParser inputParser = new InputParser();
        try {
            inputParser.createCommandActionFrom((String) userInputs.get(currentQueueSize-1));
            CommandAction commandAction = inputParser.getCommandAction();
            Queue.getInstance().addGameOutput("All good with [" + commandAction.getCommand() + "] [" + commandAction.getActionType() + "] [" + commandAction.getActionIdentifier() + "]!");
            History.getInstance().addEnteredCommand(commandAction);
            System.out.println(History.getInstance().getEnteredCommands());
        } catch (InvalidUserInputException e) {
            Queue.getInstance().addGameOutput(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.getThread().start();
    }
}
