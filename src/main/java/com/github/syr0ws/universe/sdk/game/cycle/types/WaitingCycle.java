package com.github.syr0ws.universe.sdk.game.cycle.types;

import com.github.syr0ws.universe.sdk.game.cycle.DefaultGameCycle;
import com.github.syr0ws.universe.sdk.listeners.PreRunningListener;
import com.github.syr0ws.universe.sdk.listeners.WaitingListener;
import com.github.syr0ws.universe.sdk.Game;
import com.github.syr0ws.universe.api.game.controller.GameController;
import com.github.syr0ws.universe.api.game.model.GameModel;
import com.github.syr0ws.universe.api.game.model.GameState;
import com.github.syr0ws.universe.sdk.listeners.ListenerManager;

public abstract class WaitingCycle extends DefaultGameCycle {

    public WaitingCycle(Game game, GameModel model, GameController controller) {
        super(game, model, controller);
    }

    @Override
    public void registerListeners(ListenerManager manager) {
        manager.addListener(new PreRunningListener(super.getModel(), super.getController()));
        manager.addListener(new WaitingListener(super.getModel(), super.getController()));
    }

    @Override
    public GameState getGameState() {
        return GameState.WAITING;
    }
}
