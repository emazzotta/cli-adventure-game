package com.mazzotta.kuster.clickpoint.game.commands;

import java.util.ArrayList;

public enum ActionIdentifier {
    NONE, X, Y, Z, SLIMEMONSTER, HANNIBAL, GRUNGE, THEWIZARDS;

    public static ActionIdentifier convertToActionIdentifier(String actionIdentifierText) {
        for (ActionIdentifier actionIdentifier : ActionIdentifier.values()) {
            if (actionIdentifier.name().equals(actionIdentifierText.toUpperCase())) {
                return actionIdentifier;
            }
        }
        return NONE;
    }
    
    public static boolean contains(String actionIdentifierToLookFor) {
        for (ActionIdentifier actionIdentifier : ActionIdentifier.values()) {
            if (actionIdentifier.name().equals(actionIdentifierToLookFor.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<String> thatAreAvailable() {
        ArrayList<String> availableActionIdentifiers = new ArrayList<String>();
        for (ActionIdentifier actionIdentifier : ActionIdentifier.values()) {
            availableActionIdentifiers.add(actionIdentifier.name());
        }
        return availableActionIdentifiers;
    }
}
