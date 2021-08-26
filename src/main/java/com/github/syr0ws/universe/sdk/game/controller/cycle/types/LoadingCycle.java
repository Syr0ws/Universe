package com.github.syr0ws.universe.sdk.game.controller.cycle.types;

import com.github.syr0ws.universe.api.game.controller.GameController;
import com.github.syr0ws.universe.api.game.model.GameModel;
import com.github.syr0ws.universe.api.game.model.GameState;
import com.github.syr0ws.universe.sdk.Game;
import com.github.syr0ws.universe.sdk.game.controller.cycle.DefaultGameCycle;
import com.github.syr0ws.universe.sdk.listeners.GameLoadingListener;
import com.github.syr0ws.universe.sdk.listeners.ListenerManager;

public abstract class LoadingCycle extends DefaultGameCycle {

    public LoadingCycle(Game game, GameModel model, GameController controller) {
        super(game, model, controller);
    }

    @Override
    public void registerListeners(ListenerManager manager) {
        manager.addListener(new GameLoadingListener());
    }

    @Override
    public GameState getGameState() {
        return GameState.LOADING;
    }
}
