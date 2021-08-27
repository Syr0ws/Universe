package com.github.syr0ws.universe.sdk.game.controller.cycle.types;

import com.github.syr0ws.universe.sdk.game.controller.cycle.AbstractGameCycle;
import com.github.syr0ws.universe.sdk.listeners.GamePreRunningListener;
import com.github.syr0ws.universe.sdk.listeners.GameStartingListener;
import com.github.syr0ws.universe.sdk.Game;
import com.github.syr0ws.universe.api.game.controller.GameController;
import com.github.syr0ws.universe.api.game.model.GameModel;
import com.github.syr0ws.universe.api.game.model.GameState;
import com.github.syr0ws.universe.sdk.listeners.ListenerManager;

public abstract class GameStartingCycle extends AbstractGameCycle {

    public GameStartingCycle(Game game, GameModel model, GameController controller) {
        super(game, model, controller);
    }

    @Override
    public void registerListeners(ListenerManager manager) {
        manager.addListener(new GamePreRunningListener(super.getModel(), super.getController()));
        manager.addListener(new GameStartingListener(super.getModel(), super.getController()));
    }

    @Override
    public GameState getGameState() {
        return GameState.STARTING;
    }
}
