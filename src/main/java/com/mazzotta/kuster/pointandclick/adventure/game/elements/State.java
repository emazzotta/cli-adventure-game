package com.mazzotta.kuster.pointandclick.adventure.game.elements;

import com.mazzotta.kuster.pointandclick.adventure.commands.Queue;
import com.mazzotta.kuster.pointandclick.adventure.exceptions.UserDiedException;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.characters.Player;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.items.Item;
import com.mazzotta.kuster.pointandclick.adventure.level.Initializer;

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
        if (currentRoom.hasNextRoom()) {
            if (!currentRoom.hasUndefeatedMonster()) {
                currentRoom = currentRoom.getNextRoom();
                Queue.getInstance().addGameOutput("You have advanced to the next room.\n" + getCurrentRoom().getRoomContent());
            } else {
                Queue.getInstance().addGameOutput("WARNING! You cannot advance to the next room before you have defeated: " + currentRoom.getMonster().getName() + "!!");
            }
        } else {
            Queue.getInstance().addGameOutput("" +
                    "WOHOOO!! Congratulations! You made it all the way through!!\n" +
                    "You know what you get? (Scroll down to find out...)" +
                    "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                    "You get nothing.\n" +
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
        try {
            if (currentRoom.hasUndefeatedMonster()) {
                playerHitsMonsterAndMonsterHitsPlayer();
            } else {
                Queue.getInstance().addGameOutput("What are you trying to attack? There's noting here...");
            }
        } catch (UserDiedException e) {
            Initializer.getInstance().initialise();
            Queue.getInstance().addGameOutput("You have died. Try again or type 'LOAD' to load a saved game\n");
        }
    }

    private void playerHitsMonsterAndMonsterHitsPlayer() throws UserDiedException {
        String fightOutput = playerHitsMonster();
        if (currentRoom.getMonster().isAlive()) {
            fightOutput += monsterHitsPlayer();
        } else {
            fightOutput += getMonsterWasDefeatedMessage();
            dropMonsterLoot();
        }
        Queue.getInstance().addGameOutput(fightOutput);
    }

    private String monsterHitsPlayer() throws UserDiedException {
        player.takeDamage(currentRoom.getMonster().getAttackPoints());
        return getMonsterHasAttackedMessage();
    }

    private String playerHitsMonster() {
        currentRoom.getMonster().takeDamage(player.getAttackPoints());
        return getPlayerHasAttackedMessage();
    }

    private String getMonsterWasDefeatedMessage() {
        return "\nYou have defeated " + currentRoom.getMonster().getName() + "!\nDo you think he dropped any loot...?\n";
    }

    private String getMonsterHasAttackedMessage() {
        return currentRoom.getMonster().getName() + " attacked you and inflicted " + currentRoom.getMonster().getAttackPoints() + " damage to you!\n\nMonster Health: " + currentRoom.getMonster().getHealth();
    }

    private String getPlayerHasAttackedMessage() {
        return "You attacked " + currentRoom.getMonster().getName() + " and inflicted " + player.getAttackPoints() + " damage!\n";
    }

    private void dropMonsterLoot() {
        for (Item monsterLoot : getCurrentRoom().getMonster().getLoot()) {
            getCurrentRoom().addItem(monsterLoot);
        }
    }
}
