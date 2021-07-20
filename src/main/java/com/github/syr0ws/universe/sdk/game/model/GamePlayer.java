package com.github.syr0ws.universe.sdk.game.model;

import com.github.syr0ws.universe.sdk.game.model.mode.ModeType;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface GamePlayer {

    UUID getUUID();

    String getName();

    boolean isOnline();

    boolean isPlaying();

    Player getPlayer();

    ModeType getModeType();
}
