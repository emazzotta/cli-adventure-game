package com.mazzotta.kuster.pointandclick.adventure.commands;

public class ActionIdentifier {

    int identifierId;

    public ActionIdentifier(int identifierId) {
        this.identifierId = identifierId;
    }

    public ActionIdentifier(String identifierId) {
        this.identifierId = Integer.parseInt(identifierId);
    }

    public int getIdentifierId() {
        return identifierId;
    }

    public String toString() {
        return "" + identifierId;
    }
}
