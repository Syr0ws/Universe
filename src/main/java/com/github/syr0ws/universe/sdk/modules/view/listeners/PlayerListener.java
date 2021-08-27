package com.github.syr0ws.universe.sdk.modules.view.listeners;

import com.github.syr0ws.universe.sdk.modules.view.ViewService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    private final ViewService service;

    public PlayerListener(ViewService service) {

        if(service == null)
            throw new IllegalArgumentException("ViewService cannot be null.");

        this.service = service;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        this.service.getViewHandlers().stream()
                .filter(handler -> handler.hasViews(player))
                .forEach(handler -> handler.removeViews(player));
    }
}
