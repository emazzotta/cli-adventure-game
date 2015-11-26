package com.mazzotta.kuster.pointandclick.adventure.game.elements;

import com.mazzotta.kuster.pointandclick.adventure.commands.Queue;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.characters.Player;

public class UserState {

    private static UserState instance;
    private static UserState initialUserState;
    private Room currentRoom;
    private Player player;
    private Dungeon dungeon;

    public static UserState getInstance() {
        return instance;
    }

    public static UserState createInstance(Player player, Dungeon dungeon) {
        instance = new UserState(player, dungeon);
        return instance;
    }

    private UserState(Player player, Dungeon dungeon) {
        this.player = player;
        this.dungeon = dungeon;
        this.currentRoom = dungeon.getRooms().get(0);
    }

    public void createInitialUserStateFromCurrentState() {
        initialUserState = new UserState(player, dungeon);
    }

    public void resetUserState() {
        instance = new UserState(initialUserState.getPlayer(), initialUserState.getDungeon());
    }

    public void changeRoom() {
        if(currentRoom.hasNextRoom()) {
            if (!currentRoom.hasUndefeatedMonster()) {
                currentRoom = currentRoom.getNextRoom();
                Queue.getInstance().addGameOutput("You have advanced to the next room.\nNew Room: " + currentRoom.getName());
            } else {
                Queue.getInstance().addGameOutput("WARNING! You cannot advance to the next room before you have defeated: " + currentRoom.getMonster().getName() + "!!");
            }
        }
        else {
            Queue.getInstance().addGameOutput("There is no next room available!");
        }
    }

    public Dungeon getDungeon() {
        return dungeon;
    }

    public Player getPlayer() {
        return player;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void showCurrentRoom() {
    }
}
