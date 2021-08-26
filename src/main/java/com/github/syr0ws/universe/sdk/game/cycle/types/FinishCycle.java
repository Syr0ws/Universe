package com.github.syr0ws.universe.sdk.game.cycle.types;

import com.github.syr0ws.universe.sdk.game.cycle.DefaultGameCycle;
import com.github.syr0ws.universe.sdk.Game;
import com.github.syr0ws.universe.api.game.controller.GameController;
import com.github.syr0ws.universe.api.game.model.GameModel;
import com.github.syr0ws.universe.api.game.model.GameState;

public abstract class FinishCycle extends DefaultGameCycle {

    public FinishCycle(Game game, GameModel model, GameController controller) {
        super(game, model, controller);
    }

    @Override
    public GameState getGameState() {
        return GameState.FINISHED;
    }
}
