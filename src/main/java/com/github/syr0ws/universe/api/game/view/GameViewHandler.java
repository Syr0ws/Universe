package com.github.syr0ws.universe.api.game.view;

import com.github.syr0ws.universe.api.attributes.AttributeObserver;
import com.github.syr0ws.universe.api.displays.DisplayManager;
import com.github.syr0ws.universe.api.game.model.GameState;

import java.util.Optional;

public interface GameViewHandler extends ViewHandler, AttributeObserver {

    void addViewHandler(GameStateViewHandler handler);

    void removeViewHandler(GameStateViewHandler handler);

    boolean hasViewHandler(GameStateViewHandler handler);

    Optional<GameStateViewHandler> getViewHandler(GameState state);

    DisplayManager getDisplayManager();
}
