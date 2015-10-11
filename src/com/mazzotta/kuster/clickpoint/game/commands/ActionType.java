package com.mazzotta.kuster.clickpoint.game.commands;

import java.util.ArrayList;

public enum ActionType {
    NONE, DOOR, BOOK, SHELVE, GUN, ENEMY;

    public static ActionType convertToActionType(String actionTypeText) {
        for (ActionType actionType : ActionType.values()) {
            if (actionType.name().equals(actionTypeText.toUpperCase())) {
                return actionType;
            }
        }
        return NONE;
    }
    
    public static boolean contains(String actionTypeToLookFor) {
        for (ActionType actionType : ActionType.values()) {
            if (actionType.name().equals(actionTypeToLookFor.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<String> thatAreAvailable() {
        ArrayList<String> availableactionTypes = new ArrayList<String>();
        for (ActionType actionType : ActionType.values()) {
            availableactionTypes.add(actionType.name());
        }
        return availableactionTypes;
    }
}
