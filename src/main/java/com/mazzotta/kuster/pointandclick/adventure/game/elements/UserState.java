package com.mazzotta.kuster.pointandclick.adventure.game.elements;

import com.mazzotta.kuster.pointandclick.adventure.commands.Queue;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.characters.Player;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.exception.UserDiedException;

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
            if(!currentRoom.hasUndefeatedMonster()) {
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

    // TODO maybe make something like a session for the fight so that no other actions like "OPEN DOOR" can be triggered
    public void attackMonster() {
        StringBuilder fightOutput = new StringBuilder();
        currentRoom.getMonster().takeDamage(player.getAttackPoints());
        fightOutput.append("You attacked " + currentRoom.getMonster().getName() +
                " and inflicted " + player.getAttackPoints() + " damage!\n");
        if(currentRoom.getMonster().isAlive()) {
            try {
                player.takeDamage(currentRoom.getMonster().getAttackPoints());
                fightOutput.append(currentRoom.getMonster().getName() + " attacked you and inflicted " + currentRoom.getMonster().getAttackPoints() +
                        " damage to you!\n");
            } catch (UserDiedException e) {
                //TODO handle exception
            }
        }
        fightOutput.append(
                "Player Health:\t" + player.getHealth() + "\n" +
                "Monster Health:\t" + currentRoom.getMonster().getHealth() + "\n");
        if(!currentRoom.getMonster().isAlive()) {
           fightOutput.append("You have defeated " + currentRoom.getMonster().getName() + "!\n" +
                    "You may advance to the next room.");
        }
        Queue.getInstance().addGameOutput(fightOutput.toString());
    }
}
