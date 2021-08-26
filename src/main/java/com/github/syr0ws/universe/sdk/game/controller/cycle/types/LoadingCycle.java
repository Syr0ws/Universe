package com.github.syr0ws.universe.sdk.game.controller.cycle.types;

import com.github.syr0ws.universe.api.game.controller.GameController;
import com.github.syr0ws.universe.api.game.model.GameModel;
import com.github.syr0ws.universe.api.game.model.GameState;
import com.github.syr0ws.universe.sdk.Game;
import com.github.syr0ws.universe.sdk.game.controller.cycle.DefaultGameCycle;

public abstract class LoadingCycle extends DefaultGameCycle {

    public LoadingCycle(Game game, GameModel model, GameController controller) {
        super(game, model, controller);
    }

    @Override
    public GameState getGameState() {
        return GameState.LOADING;
    }
}
