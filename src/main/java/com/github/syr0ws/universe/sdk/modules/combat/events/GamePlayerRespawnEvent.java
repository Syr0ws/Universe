package com.github.syr0ws.universe.sdk.modules.combat.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class GamePlayerRespawnEvent extends PlayerEvent {

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    private Location respawn;

    public GamePlayerRespawnEvent(Player player) {
        super(player);
        this.respawn = player.getWorld().getSpawnLocation();
    }

    public Location getRespawnLocation() {
        return respawn;
    }

    public void setRespawnLocation(Location respawn) {

        if(respawn == null)
            throw new IllegalArgumentException("Respawn location cannot be null.");

        this.respawn = respawn;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }
}
