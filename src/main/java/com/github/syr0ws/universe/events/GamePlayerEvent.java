package com.github.syr0ws.universe.events;

import com.github.syr0ws.universe.game.model.GamePlayer;
import org.bukkit.event.Event;

public abstract class GamePlayerEvent extends Event {

    private final GamePlayer player;

    public GamePlayerEvent(GamePlayer player) {

        if(player == null)
            throw new IllegalArgumentException("GamePlayer cannot be null.");

        this.player = player;
    }

    public GamePlayer getGamePlayer() {
        return this.player;
    }
}
