package com.mazzotta.kuster.pointandclick.adventure.main;

import com.mazzotta.kuster.pointandclick.adventure.commands.CommandAction;
import com.mazzotta.kuster.pointandclick.adventure.commands.CommandHandler;
import com.mazzotta.kuster.pointandclick.adventure.commands.History;
import com.mazzotta.kuster.pointandclick.adventure.commands.Queue;
import com.mazzotta.kuster.pointandclick.adventure.level.Initialiser;

public class Game {

    private static Game instance;

    private static GUI gui;
    private static boolean showInitialText;
    private static int currentQueueSize;
    public static boolean running;

    public static Game getInstance() {
        if(instance == null) {
            instance = new Game();
        }
        return instance;
    }

    private Game() {
        running = false;
        showInitialText = true;
        currentQueueSize = Queue.getInstance().getPendingUserInput().size();
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
                        if(showInitialText) {
                            showInitialText = false;
                            Queue.getInstance().addGameOutput(infoText());
                            gui.updateGUI();
                            Queue.getInstance().clearGameOutputCache();
                        }
                        if (Queue.getInstance().getPendingUserInput().size() > currentQueueSize) {
                            currentQueueSize = Queue.getInstance().getPendingUserInput().size();
                            Game.handleNewQueueItem();

                            gui.updateGUI();

                            Queue.getInstance().clearUserInputCache();
                            Queue.getInstance().clearGameOutputCache();
                            currentQueueSize = 0;
                        }
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        };
    }

    public static void handleNewQueueItem() {
            CommandAction commandAction = Queue.getInstance().getPendingUserInput().get(currentQueueSize-1);
            Queue.getInstance().addGameOutput("OK: " + commandAction.getCommand() + " " + commandAction.getActionType() + " " + commandAction.getActionIdentifier());
            History.getInstance().addEnteredCommand(commandAction);
            new CommandHandler(commandAction).executeCommand();
    }

    private String infoText() {
        return "Hello Player! Yes, another one of those stereotypical games. \n" +
                "Fight the boss win the game, simple enough right?\n" +
                "In case you need help with the commands, type \"HELP\"\n\n" +
                "If you want to resume your previous session, load your saved file like this:\n" +
                "LOAD my_session\n\n" +
                "Enjoy! - A Mazzotta & Kuster production\n" +
                "<Hier könnte Ihre Werbung stehen!>\n\n";
    }

    public static void main(String[] args) {
        getInstance().getThread().start();
    }
}
