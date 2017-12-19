package com.mazzotta.kuster.pointandclick.adventure.commands;

public class ActionIdentifier {

    private String identifierId;

    ActionIdentifier(String identifierId) {
        this.identifierId = identifierId;
    }

    public String getIdentifierId() {
        return identifierId;
    }

    public String toString() {
        return identifierId;
    }
}
