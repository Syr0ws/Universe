package com.github.syr0ws.universe.sdk.events;

import com.github.syr0ws.universe.api.game.model.GamePlayer;
import org.bukkit.event.HandlerList;

public class GamePlayerQuitEvent extends GamePlayerEvent {

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    public GamePlayerQuitEvent(GamePlayer player) {
        super(player);
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }
}
