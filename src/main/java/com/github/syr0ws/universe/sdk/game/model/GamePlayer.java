package com.github.syr0ws.universe.sdk.game.model;

import org.bukkit.entity.Player;

import java.util.UUID;

public interface GamePlayer {

    UUID getUUID();

    String getName();

    boolean isOnline();

    boolean isPlaying();

    Player getPlayer();
}
