package com.mazzotta.kuster.pointandclick.adventure.level;

import com.mazzotta.kuster.pointandclick.adventure.commands.History;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.Dungeon;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.Room;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.State;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.characters.Monster;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.characters.Player;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.items.Potion;
import com.mazzotta.kuster.pointandclick.adventure.game.elements.items.Weapon;
import com.mazzotta.kuster.pointandclick.adventure.main.Game;

public class Initializer {

    private Dungeon dungeon;
    private Player player;
    private static Initializer instance;

    private Initializer() {
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
        History.getInstance().clearCommands();
        Game.showInitialText = true;
        State.createInstance(player, dungeon);
    }

    private void createPlayer() {
        Weapon weakSword = new Weapon("Weak Sword", 50);
        Potion healthPotion25 = new Potion(25);

        player = new Player(500, weakSword);
        player.getInventory().addWeapon(weakSword);
        player.getInventory().addPotion(healthPotion25);
    }

    private void addRoomsToDungeon() {
        dungeon = new Dungeon();

        Room room9 = new Room("Final Room", null);
        Room room8 = new Room("Room 8", room9);
        Room room7 = new Room("Room 7", room8);
        Room room6 = new Room("Room 6", room7);
        Room room5 = new Room("Room 5", room6);
        Room room4 = new Room("Room 4", room5);
        Room room3 = new Room("Room 3", room4);
        Room room2 = new Room("Room 2", room3);
        Room room1 = new Room("Starting Room", room2);

        dungeon.addRoom(room1);
        dungeon.addRoom(room2);
        dungeon.addRoom(room3);
        dungeon.addRoom(room4);
        dungeon.addRoom(room5);
        dungeon.addRoom(room6);
        dungeon.addRoom(room7);
        dungeon.addRoom(room8);
        dungeon.addRoom(room9);
    }

    private void addItemsToRooms() {
        Weapon shortSword = new Weapon("Short Sword", 100);
        Weapon longSword = new Weapon("Long Sword", 200);
        Weapon masterSword = new Weapon("Master Sword", 500);

        Potion healthPotion50 = new Potion(50);
        Potion healthPotion100 = new Potion(100);
        Potion healthPotion150 = new Potion(150);

        dungeon.getRooms().get(1).addItem(shortSword);
        dungeon.getRooms().get(2).addItem(healthPotion50);
        dungeon.getRooms().get(2).addItem(healthPotion100);
        dungeon.getRooms().get(6).addItem(longSword);
        dungeon.getRooms().get(7).addItem(healthPotion150);
        dungeon.getRooms().get(8).addItem(masterSword);
    }

    private void addMonstersToRooms() {
        Monster monsterEasy = new Monster("Bowaa", 12, 50, new Potion(10));
        Monster monsterMedium = new Monster("Zazinzongi", 40, 250, new Weapon("Crazy Thing", 200));
        Monster monsterHard = new Monster("ARGHHAKASH", 80, 500, new Potion(200), new Weapon("The Force With You", 250));
        Monster monsterEndbaawwws = new Monster("BLOOOAAATTTOOON", 150, 1000, new Weapon("Over 9000!", 9001));

        dungeon.getRooms().get(3).setMonster(monsterEasy);
        dungeon.getRooms().get(5).setMonster(monsterMedium);
        dungeon.getRooms().get(7).setMonster(monsterHard);
        dungeon.getRooms().get(8).setMonster(monsterEndbaawwws);
    }
}
