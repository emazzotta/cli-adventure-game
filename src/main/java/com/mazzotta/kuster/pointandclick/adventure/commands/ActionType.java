package com.mazzotta.kuster.pointandclick.adventure.commands;

public enum ActionType {
    NONE, DOOR, MONSTER, INVENTORY, ITEMS, POTION, ROOM, GAME;

    public static ActionType convertToActionType(String actionTypeText) {
        for(ActionType actionType : ActionType.values()) {
            if(actionType.name().equals(actionTypeText.toUpperCase())) {
                return actionType;
            }
        }
        return NONE;
    }
    
    public static boolean contains(String actionTypeToLookFor) {
        for(ActionType actionType : ActionType.values()) {
            if(actionType.name().equals(actionTypeToLookFor.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    public static String listAvailable() {
        String availableActionTypes = "";
        for(ActionType actionType : ActionType.values()) {
            availableActionTypes += actionType.name() + "\n";
        }
        return availableActionTypes;
    }
}
