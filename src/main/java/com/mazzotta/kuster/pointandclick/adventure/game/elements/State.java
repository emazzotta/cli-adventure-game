package com.mazzotta.kuster.pointandclick.adventure.game.elements;

import com.mazzotta.kuster.pointandclick.adventure.commands.Queue;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.characters.Player;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.exception.UserDiedException;

public class State {

    private static State instance;
    private Room currentRoom;
    private Player player;

    public static State getInstance() {
        return instance;
    }

    public static State createInstance(Player player, Dungeon dungeon) {
        instance = new State(player, dungeon);
        return instance;
    }

    private State(Player player, Dungeon dungeon) {
        this.player = player;
        this.currentRoom = dungeon.getRooms().get(0);
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

    public Player getPlayer() {
        return player;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void attackMonster() {
        if(currentRoom.hasUndefeatedMonster()) {
            currentRoom.getMonster().takeDamage(player.getAttackPoints());

            String fightOutput = "You attacked " + currentRoom.getMonster().getName() + " and inflicted " + player.getAttackPoints() + " damage!\n";
            if (currentRoom.getMonster().isAlive()) {
                try {
                    player.takeDamage(currentRoom.getMonster().getAttackPoints());
                    fightOutput += currentRoom.getMonster().getName() +
                            " attacked you and inflicted " +
                            currentRoom.getMonster().getAttackPoints() +
                            " damage to you!\n";
                } catch (UserDiedException e) {
                    //TODO handle exception
                }
            }
            fightOutput += "Player Health: " +
                    player.getHealth() + "\n" +
                    "Monster Health: " +
                    currentRoom.getMonster().getHealth() + "\n";
            if (!currentRoom.getMonster().isAlive()) {
                fightOutput += "You have defeated " +
                        currentRoom.getMonster().getName() + "!\n" +
                        "You may advance to the next room.";
            }
            Queue.getInstance().addGameOutput(fightOutput);
        } else {
            Queue.getInstance().addGameOutput("What are you trying to attack? There's noting here...");
        }
    }
}
