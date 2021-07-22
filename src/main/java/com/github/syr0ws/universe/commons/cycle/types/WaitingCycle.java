package com.github.syr0ws.universe.commons.cycle.types;

import com.github.syr0ws.universe.commons.cycle.DefaultGameCycle;
import com.github.syr0ws.universe.commons.listeners.PreRunningListener;
import com.github.syr0ws.universe.commons.listeners.WaitingListener;
import com.github.syr0ws.universe.sdk.Game;
import com.github.syr0ws.universe.sdk.game.controller.GameController;
import com.github.syr0ws.universe.sdk.game.model.GameModel;
import com.github.syr0ws.universe.sdk.game.model.GameState;
import com.github.syr0ws.universe.sdk.listeners.ListenerManager;

public class WaitingCycle extends DefaultGameCycle {

    public WaitingCycle(Game game, GameModel model, GameController controller) {
        super(game, model, controller);
    }

    @Override
    public void load() {
        super.load();
        this.registerListeners();
    }

    @Override
    public GameState getGameState() {
        return GameState.WAITING;
    }

    private void registerListeners() {

        ListenerManager manager = super.getListenerManager();
        manager.addListener(new PreRunningListener(super.getModel(), super.getController()));
        manager.addListener(new WaitingListener(super.getModel(), super.getController()));
    }
}
