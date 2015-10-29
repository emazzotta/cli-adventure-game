package com.mazzotta.kuster.pointandclick.adventure.level;

public class Initialiser {

    private static Initialiser instance;

    private Initialiser() {

    }

    public static Initialiser getInstance() {
        if(instance == null) {
            instance = new Initialiser();
        }
        return instance;
    }


    public void initialise() {
        // TODO this will initialise all the possible rooms with their weapons
    }
}
