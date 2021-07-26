package com.github.syr0ws.universe.commons.model;

import com.github.syr0ws.universe.sdk.game.model.GamePlayer;
import com.github.syr0ws.universe.sdk.game.mode.ModeType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class DefaultGamePlayer implements GamePlayer {

    private final UUID uuid;
    private final String name;

    private ModeType modeType;
    private boolean playing;

    public DefaultGamePlayer(Player player) {

        if(player == null)
            throw new IllegalArgumentException("Player cannot be null.");

        this.uuid = player.getUniqueId();
        this.name = player.getName();
    }

    public void setModeType(ModeType type) {

        if(type == null)
            throw new IllegalArgumentException("ModeType cannot be null.");

        this.modeType = type;
    }

    public void setPlaying() {
        this.playing = true;
    }

    @Override
    public UUID getUUID() {
        return this.uuid;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean isPlaying() {
        return this.playing;
    }

    @Override
    public boolean isOnline() {
        return Bukkit.getPlayer(this.uuid) != null;
    }

    @Override
    public Player getPlayer() {
        return Bukkit.getPlayer(this.uuid);
    }

    @Override
    public ModeType getModeType() {
        return this.modeType;
    }
}
