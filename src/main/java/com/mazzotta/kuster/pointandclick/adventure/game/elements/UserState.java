package com.mazzotta.kuster.pointandclick.adventure.game.elements;

import com.mazzotta.kuster.pointandclick.adventure.game.elements.characters.Player;

public class UserState {

    private static UserState instance;
    private Room currentRoom;
    private Player player;
    private Dungeon dungeon;

    public static UserState getInstance() throws Exception {
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
}
