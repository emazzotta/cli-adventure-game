package com.mazzotta.kuster.pointandclick.adventure.game.elements;

import com.mazzotta.kuster.pointandclick.adventure.commands.Queue;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.characters.Player;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.exception.UserDiedException;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.items.Item;

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
                Queue.getInstance().addGameOutput("You have advanced to the next room.\n" + getCurrentRoom().getRoomContent());
            } else {
                Queue.getInstance().addGameOutput("WARNING! You cannot advance to the next room before you have defeated: " + currentRoom.getMonster().getName() + "!!");
            }
        }
        else {
            Queue.getInstance().addGameOutput("WOHOOO!! Congratulations! You made it all the way through!!\n" +
                    "You know what you get? (Scroll down to find out...)" +
                    "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nYou get nothing.\n" +
                    "But thanks for playing!");
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
            String fightOutput = "You attacked ";
            fightOutput += currentRoom.getMonster().getName();
            fightOutput += " and inflicted ";
            fightOutput += player.getAttackPoints();
            fightOutput += " damage!\n";
            if (currentRoom.getMonster().isAlive()) {
                try {
                    player.takeDamage(currentRoom.getMonster().getAttackPoints());
                    fightOutput += currentRoom.getMonster().getName();
                    fightOutput += " attacked you and inflicted ";
                    fightOutput += currentRoom.getMonster().getAttackPoints();
                    fightOutput += " damage to you!\n";
                } catch (UserDiedException e) {
                    //TODO handle exception
                }
            }
            fightOutput += "Player Health: ";
            fightOutput += player.getHealth();
            fightOutput += "\nMonster Health: ";
            fightOutput += currentRoom.getMonster().getHealth();
            if (!currentRoom.getMonster().isAlive()) {
                fightOutput += "\nYou have defeated ";
                fightOutput += currentRoom.getMonster().getName() + "!\n";
                dropMonsterLoot();
            }
            Queue.getInstance().addGameOutput(fightOutput);
        } else {
            Queue.getInstance().addGameOutput("What are you trying to attack? There's noting here...");
        }
    }

    private void dropMonsterLoot() {
        for (Item monsterLoot : getCurrentRoom().getMonster().getLoot()) {
            getCurrentRoom().addItem(monsterLoot);
        }
    }
}
