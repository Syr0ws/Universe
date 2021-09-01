package com.github.syr0ws.universe.api.game.view;

import com.github.syr0ws.universe.api.game.model.GameState;

public interface GameStateViewHandler extends ViewHandler {

    GameState getState();
}
