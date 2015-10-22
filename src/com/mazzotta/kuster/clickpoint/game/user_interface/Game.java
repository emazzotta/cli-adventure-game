package com.mazzotta.kuster.clickpoint.game.user_interface;

import com.mazzotta.kuster.clickpoint.game.commands.CommandAction;
import com.mazzotta.kuster.clickpoint.game.commands.Queue;
import com.mazzotta.kuster.clickpoint.game.commands.parsing.InputParser;
import com.mazzotta.kuster.clickpoint.game.commands.parsing.InvalidUserInputException;

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
        currentQueueSize = Queue.getInstance().getOutStandingUserInput().size();
        checkInput = getThread();
        gui = new GUI();
    }

    private Thread getThread() {
        return new Thread() {
            public void run() {
                running = true;
                while (running) {
                    try {
                        Thread.sleep(100);
                        System.out.println("Test");
                        if (Queue.getInstance().getOutStandingUserInput().size() > currentQueueSize) {
                            currentQueueSize = Queue.getInstance().getOutStandingUserInput().size();
                            Game.newQueueItemAvailable();
                        }
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        };
    }

    public static void newQueueItemAvailable() {
        ArrayList userInputs = Queue.getInstance().getOutStandingUserInput();
        InputParser inputParser = new InputParser();
        CommandAction commandAction = new CommandAction();

        try {
            inputParser.createCommandActionFrom((String) userInputs.get(currentQueueSize-1));
            commandAction = inputParser.getCommandAction();
            Queue.getInstance().addGameOutput("All good with [" + commandAction.getCommand() + "] [" + commandAction.getActionType() + "] [" + commandAction.getActionIdentifier() + "]!");

        } catch (InvalidUserInputException e) {
            Queue.getInstance().addGameOutput(e.getMessage());
        }



    }

    public static void main(String[] args) {
        Game game = new Game();
        game.getThread().start();
    }
}
