package com.github.syr0ws.universe.game.model;

import com.github.syr0ws.universe.game.model.mode.Mode;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface GamePlayer {

    UUID getUUID();

    String getName();

    Mode getMode();

    boolean isOnline();

    boolean isPlaying();

    Player getPlayer();
}
