package com.github.syr0ws.universe.sdk.game.controller.cycle.types;

import com.github.syr0ws.universe.sdk.game.controller.cycle.DefaultGameCycle;
import com.github.syr0ws.universe.sdk.listeners.PreRunningListener;
import com.github.syr0ws.universe.sdk.listeners.StartingListener;
import com.github.syr0ws.universe.sdk.Game;
import com.github.syr0ws.universe.api.game.controller.GameController;
import com.github.syr0ws.universe.api.game.model.GameModel;
import com.github.syr0ws.universe.api.game.model.GameState;
import com.github.syr0ws.universe.sdk.listeners.ListenerManager;

public abstract class StartingCycle extends DefaultGameCycle {

    public StartingCycle(Game game, GameModel model, GameController controller) {
        super(game, model, controller);
    }

    @Override
    public void registerListeners(ListenerManager manager) {
        manager.addListener(new PreRunningListener(super.getModel(), super.getController()));
        manager.addListener(new StartingListener(super.getModel(), super.getController()));
    }

    @Override
    public GameState getGameState() {
        return GameState.STARTING;
    }
}
