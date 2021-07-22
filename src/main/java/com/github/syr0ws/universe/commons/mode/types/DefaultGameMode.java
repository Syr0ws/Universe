package com.github.syr0ws.universe.commons.mode.types;

import com.github.syr0ws.universe.sdk.game.mode.Mode;
import com.github.syr0ws.universe.sdk.game.model.GameModel;

public abstract class DefaultGameMode implements Mode {

    private final GameModel model;

    public DefaultGameMode(GameModel model) {

        if(model == null)
            throw new IllegalArgumentException("GameModel cannot be null.");

        this.model = model;
    }

    public GameModel getModel() {
        return this.model;
    }
}
