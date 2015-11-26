package com.mazzotta.kuster.pointandclick.adventure.commands;

import com.mazzotta.kuster.pointandclick.adventure.level.Initializer;
import com.mazzotta.kuster.pointandclick.adventure.main.Game;

public class CommandUtil {

    public static void resetGame() {
        History.getInstance().clearCommands();
        Initializer.getInstance().initialise();
    }

    public static void resetGameAndDisplayInitialText() {
        resetGame();
        Game.showInitialText = true;
    }
}
