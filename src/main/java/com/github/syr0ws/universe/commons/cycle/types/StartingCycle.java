package com.github.syr0ws.universe.commons.cycle.types;

import com.github.syr0ws.universe.commons.cycle.DefaultGameCycle;
import com.github.syr0ws.universe.commons.listeners.PreRunningListener;
import com.github.syr0ws.universe.sdk.Game;
import com.github.syr0ws.universe.sdk.game.controller.GameController;
import com.github.syr0ws.universe.sdk.game.model.GameModel;
import com.github.syr0ws.universe.sdk.game.model.GameState;
import com.github.syr0ws.universe.sdk.listeners.ListenerManager;

public class StartingCycle extends DefaultGameCycle {

    public StartingCycle(Game game, GameModel model, GameController controller) {
        super(game, model, controller);
    }

    @Override
    public GameState getGameState() {
        return GameState.STARTING;
    }

    private void registerListeners() {

        ListenerManager manager = super.getListenerManager();
        manager.addListener(new PreRunningListener(super.getModel(), super.getController()));
    }
}
