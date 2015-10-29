package com.mazzotta.kuster.pointandclick.adventure.commands;

import java.util.ArrayList;

public enum ActionIdentifier {
    NONE, RED, BLUE, GREEN, YELLOW, WHITE, ALL;

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
