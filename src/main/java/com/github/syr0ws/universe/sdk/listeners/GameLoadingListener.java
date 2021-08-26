package com.github.syr0ws.universe.sdk.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class GameLoadingListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJoin(AsyncPlayerPreLoginEvent event) {
        event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
    }
}
