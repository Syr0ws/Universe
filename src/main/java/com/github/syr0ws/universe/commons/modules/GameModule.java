package com.github.syr0ws.universe.commons.modules;

import com.github.syr0ws.universe.sdk.Game;
import com.github.syr0ws.universe.sdk.listeners.ListenerManager;
import org.bukkit.configuration.file.FileConfiguration;

public abstract class GameModule implements Module {

    private final Game game;
    private final ListenerManager listenerManager;

    public GameModule(Game game) {

        if(game == null)
            throw new IllegalArgumentException("Game cannot be null.");

        this.game = game;
        this.listenerManager = new ListenerManager(game);
    }

    public abstract String getName();

    public Game getGame() {
        return this.game;
    }

    public FileConfiguration getConfig() {
        return this.game.getConfig();
    }

    public ListenerManager getListenerManager() {
        return this.listenerManager;
    }
}
