package com.github.syr0ws.universe.sdk.game.cycle.types;

import com.github.syr0ws.universe.sdk.game.cycle.DefaultGameCycle;
import com.github.syr0ws.universe.sdk.listeners.RunningListener;
import com.github.syr0ws.universe.sdk.Game;
import com.github.syr0ws.universe.api.game.controller.GameController;
import com.github.syr0ws.universe.api.game.model.GameModel;
import com.github.syr0ws.universe.api.game.model.GameState;
import com.github.syr0ws.universe.sdk.listeners.ListenerManager;

public class RunningCycle extends DefaultGameCycle {

    public RunningCycle(Game game, GameModel model, GameController controller) {
        super(game, model, controller);
    }

    @Override
    public void enable() {
        this.registerListeners();
        super.enable();
    }

    @Override
    public GameState getGameState() {
        return GameState.RUNNING;
    }

    private void registerListeners() {

        ListenerManager manager = super.getListenerManager();
        manager.addListener(new RunningListener(super.getModel(), super.getController()));
    }
}
