package com.github.syr0ws.universe.sdk.game.view;

import com.github.syr0ws.universe.api.game.view.GameStateViewHandler;
import com.github.syr0ws.universe.sdk.Game;

public abstract class AbstractGameStateViewHandler extends AbstractViewHandler implements GameStateViewHandler {

    public AbstractGameStateViewHandler(Game game) {
        super(game);
    }
}
