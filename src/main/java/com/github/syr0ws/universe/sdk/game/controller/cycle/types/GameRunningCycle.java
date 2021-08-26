package com.github.syr0ws.universe.sdk.game.controller.cycle.types;

import com.github.syr0ws.universe.sdk.game.controller.cycle.AbstractGameCycle;
import com.github.syr0ws.universe.sdk.listeners.GameRunningListener;
import com.github.syr0ws.universe.sdk.Game;
import com.github.syr0ws.universe.api.game.controller.GameController;
import com.github.syr0ws.universe.api.game.model.GameModel;
import com.github.syr0ws.universe.api.game.model.GameState;
import com.github.syr0ws.universe.sdk.listeners.ListenerManager;

public abstract class GameRunningCycle extends AbstractGameCycle {

    public GameRunningCycle(Game game, GameModel model, GameController controller) {
        super(game, model, controller);
    }

    @Override
    public void registerListeners(ListenerManager manager) {
        manager.addListener(new GameRunningListener(super.getModel(), super.getController()));
    }

    @Override
    public GameState getGameState() {
        return GameState.RUNNING;
    }
}
