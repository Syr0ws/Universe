package com.github.syr0ws.universe.modules.scoreboard;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class ScoreboardListener implements Listener {

    private final ScoreboardManager manager;

    public ScoreboardListener(ScoreboardManager manager) {

        if(manager == null)
            throw new IllegalArgumentException("ScoreboardManager cannot be null.");

        this.manager = manager;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        // If the player has a scoreboard, removing it.
        if(this.manager.hasScoreboard(player)) this.manager.removeScoreboard(player);
    }
}
