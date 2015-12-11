package com.mazzotta.kuster.pointandclick.adventure.game.elements;

import com.mazzotta.kuster.pointandclick.adventure.commands.CommandAction;
import com.mazzotta.kuster.pointandclick.adventure.commands.CommandHandler;
import com.mazzotta.kuster.pointandclick.adventure.commands.Queue;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.characters.Player;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.exception.UserDiedException;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.items.Item;

import javax.management.QueryEval;

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
        StringBuilder fightOutput = new StringBuilder();
        try {
            if (currentRoom.hasUndefeatedMonster()) {
                currentRoom.getMonster().takeDamage(player.getAttackPoints());
                fightOutput.append("You attacked ");
                fightOutput.append(currentRoom.getMonster().getName());
                fightOutput.append(" and inflicted ");
                fightOutput.append(player.getAttackPoints());
                fightOutput.append(" damage!\n");
                if (currentRoom.getMonster().isAlive()) {

                    player.takeDamage(currentRoom.getMonster().getAttackPoints());
                    fightOutput.append(currentRoom.getMonster().getName());
                    fightOutput.append(" attacked you and inflicted ");
                    fightOutput.append(currentRoom.getMonster().getAttackPoints());
                    fightOutput.append(" damage to you!\n");

                }
                fightOutput.append("Player Health: ");
                fightOutput.append(player.getHealth());
                fightOutput.append("\nMonster Health: ");
                fightOutput.append(currentRoom.getMonster().getHealth());
                if (!currentRoom.getMonster().isAlive()) {
                    fightOutput.append("\nYou have defeated ");
                    fightOutput.append(currentRoom.getMonster().getName());
                    fightOutput.append("!\nDo you think he dropped any loot...?\n");
                    dropMonsterLoot();
                }
                Queue.getInstance().addGameOutput(fightOutput.toString());
            } else {
                Queue.getInstance().addGameOutput("What are you trying to attack? There's noting here...");
            }
        } catch (UserDiedException e) {
            Queue.getInstance().clearGameOutputCache();
            CommandHandler.execute(new CommandAction(new String[]{"RESET"}));

            Queue.getInstance().addGameOutput("---------------------------------\n" +
                    "You have died. Try again or type 'LOAD' to load a saved game\n" +
                    "---------------------------------");
        }
    }

    private void dropMonsterLoot() {
        for (Item monsterLoot : getCurrentRoom().getMonster().getLoot()) {
            getCurrentRoom().addItem(monsterLoot);
        }
    }
}
