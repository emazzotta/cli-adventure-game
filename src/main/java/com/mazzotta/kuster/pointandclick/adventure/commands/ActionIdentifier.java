package com.mazzotta.kuster.pointandclick.adventure.commands;

public class ActionIdentifier {

    String identifierId;

    public ActionIdentifier(String identifierId) {
        this.identifierId = identifierId;
    }

    public String getIdentifierId() {
        return identifierId;
    }

    public int toInt() {
        return Integer.parseInt(identifierId);
    }
}
