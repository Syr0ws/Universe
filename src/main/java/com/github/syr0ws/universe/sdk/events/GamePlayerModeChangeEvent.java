package com.github.syr0ws.universe.sdk.events;

import com.github.syr0ws.universe.api.game.model.GamePlayer;
import com.github.syr0ws.universe.api.game.mode.Mode;
import org.bukkit.event.HandlerList;

public class GamePlayerModeChangeEvent extends GamePlayerEvent {

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    private Mode mode;

    public GamePlayerModeChangeEvent(GamePlayer player, Mode mode) {
        super(player);
        this.setMode(mode);
    }

    public Mode getMode() {
        return this.mode;
    }

    public void setMode(Mode mode) {

        if(mode == null)
            throw new IllegalArgumentException("Mode cannot be null.");

        this.mode = mode;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }
}
