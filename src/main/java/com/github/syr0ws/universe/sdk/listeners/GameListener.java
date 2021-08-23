package com.github.syr0ws.universe.sdk.listeners;

import com.github.syr0ws.universe.sdk.modules.combat.events.GamePlayerRespawnEvent;
import com.github.syr0ws.universe.api.game.model.GameModel;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class GameListener implements Listener {

    private final GameModel model;

    public GameListener(GameModel model) {

        if(model == null)
            throw new IllegalArgumentException("GameModel cannot be null.");

        this.model = model;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerRespawn(GamePlayerRespawnEvent event) {

        Location spawn = this.model.getSpawn();
        event.setRespawnLocation(spawn); // Using game spawn as default respawn location.
    }
}
