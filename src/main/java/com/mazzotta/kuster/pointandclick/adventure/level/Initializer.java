package com.mazzotta.kuster.pointandclick.adventure.level;

import com.mazzotta.kuster.pointandclick.adventure.game.elements.Dungeon;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.Room;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.UserState;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.characters.Monster;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.characters.Player;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.items.Potion;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.items.Weapon;

public class Initializer {

    private Dungeon dungeon;
    private Player player;
    private static Initializer instance;

    private Initializer() {
        dungeon = new Dungeon();
    }

    public static Initializer getInstance() {
        if(instance == null) {
            instance = new Initializer();
        }
        return instance;
    }

    public void initialise() {
        createPlayer();
        addRoomsToDungeon();
        addItemsToRooms();
        addMonstersToRooms();
        UserState.createInstance(player, dungeon);
        UserState.getInstance().createInitialUserStateFromCurrentState();
    }

    private void createPlayer() {
        Weapon weakSword = new Weapon("Weak Sword", 50);
        Potion healthPotion25 = new Potion("Health +25", 25);

        player = new Player("Test Player", 500, weakSword);
        player.getInventory().addPotion(healthPotion25);
    }

    private void addMonstersToRooms() {
        Monster monsterEasy = new Monster("Bowaa", 12, 50);
        Monster monsterMedium = new Monster("Zazinzongi", 12, 250);
        Monster monsterHard = new Monster("ARGHHAKASH", 12, 500);
        Monster monsterEndbaawwws = new Monster("BLOOOAAATTTOOON", 12, 1000);

        dungeon.getRooms().get(3).setMonster(monsterEasy);
        dungeon.getRooms().get(5).setMonster(monsterMedium);
        dungeon.getRooms().get(7).setMonster(monsterHard);
        dungeon.getRooms().get(8).setMonster(monsterEndbaawwws);
    }

    private void addItemsToRooms() {
        Weapon shortSword = new Weapon("Short Sword", 100);
        Weapon longSword = new Weapon("Long Sword", 200);
        Weapon masterSword = new Weapon("Master Sword", 500);

        Potion healthPotion50 = new Potion("Health +50", 50);
        Potion healthPotion100 = new Potion("Health +100", 100);
        Potion healthPotion150 = new Potion("Health +150", 150);

        dungeon.getRooms().get(0).addItem(shortSword);
        dungeon.getRooms().get(2).addItem(healthPotion50);
        dungeon.getRooms().get(2).addItem(healthPotion100);
        dungeon.getRooms().get(6).addItem(longSword);
        dungeon.getRooms().get(7).addItem(healthPotion150);
        dungeon.getRooms().get(8).addItem(masterSword);
    }

    private void addRoomsToDungeon() {
        Room room9 = new Room("Final Room", null);
        Room room8 = new Room("Room 8", room9);
        Room room7 = new Room("Room 7", room8);
        Room room6 = new Room("Room 6", room7);
        Room room5 = new Room("Room 5", room6);
        Room room4 = new Room("Room 4", room5);
        Room room3 = new Room("Room 3", room4);
        Room room2 = new Room("Room 2", room3);
        Room room1 = new Room("Starting Room", room2);

        dungeon.getRooms().add(room1);
        dungeon.getRooms().add(room2);
        dungeon.getRooms().add(room3);
        dungeon.getRooms().add(room4);
        dungeon.getRooms().add(room5);
        dungeon.getRooms().add(room6);
        dungeon.getRooms().add(room7);
        dungeon.getRooms().add(room8);
        dungeon.getRooms().add(room9);
    }
}
