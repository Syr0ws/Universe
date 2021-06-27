package com.github.syr0ws.universe.modules;

import com.github.syr0ws.universe.Game;
import com.github.syr0ws.universe.listeners.ListenerManager;

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

    public ListenerManager getListenerManager() {
        return this.listenerManager;
    }
}
