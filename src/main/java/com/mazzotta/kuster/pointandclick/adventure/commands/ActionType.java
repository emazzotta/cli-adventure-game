package com.mazzotta.kuster.pointandclick.adventure.commands;

import java.util.ArrayList;

public enum ActionType {
    NONE, DOOR, ENEMY, INVENTORY, ITEMS, POTION, ROOM;

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
        ArrayList<String> availableActionTypes = new ArrayList<String>();
        for (ActionType actionType : ActionType.values()) {
            availableActionTypes.add(actionType.name());
        }
        return availableActionTypes;
    }
}
