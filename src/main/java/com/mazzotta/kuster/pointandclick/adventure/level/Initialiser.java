package com.mazzotta.kuster.pointandclick.adventure.level;

import com.mazzotta.kuster.pointandclick.adventure.game.elements.*;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.items.Item;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.items.Potion;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.items.Weapon;

import java.util.ArrayList;

public class Initialiser {

    private Dungeon dungeon;
    private Player player;
    private static Initialiser instance;

    private Initialiser() {
        dungeon = new Dungeon();
    }

    public static Initialiser getInstance() {
        if(instance == null) {
            instance = new Initialiser();
        }
        return instance;
    }


    public void initialise() {
        createPlayer();
        addRoomsToDungeon();
        addItemsToRooms();
        addMonstersToRooms();
        UserState.createInstance(player, dungeon);
    }

    private void createPlayer() {
        Weapon weakSword = new Weapon("Weak Sword", 50);
        Potion healthPotion25 = new Potion("Health +25", 25);

        player = new Player("Test Player", 500, weakSword);
        player.getInventory().addPotion(healthPotion25);
    }

    private void addMonstersToRooms() {
        Monster monsterEasy = new Monster("Bowaa", 12, 50);
        Monster monsterMedium = new Monster("Zazinzongi", 12, 50);
        Monster monsterHard = new Monster("ARGHHAKASH", 12, 50);
        Monster monsterEndbaawwws = new Monster("BLOOOAAATTTOOON", 12, 50);

        rooms.get(3).setMonster(monsterEasy);
        rooms.get(3).monsterDefeated = false;
        rooms.get(5).setMonster(monsterMedium);
        rooms.get(5).monsterDefeated = false;
        rooms.get(7).setMonster(monsterHard);
        rooms.get(5).monsterDefeated = false;
        rooms.get(8).setMonster(monsterEndbaawwws);
        rooms.get(5).monsterDefeated = false;
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
