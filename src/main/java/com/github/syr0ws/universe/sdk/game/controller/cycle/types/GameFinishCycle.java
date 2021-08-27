package com.github.syr0ws.universe.sdk.game.controller.cycle.types;

import com.github.syr0ws.universe.sdk.game.controller.cycle.AbstractGameCycle;
import com.github.syr0ws.universe.sdk.Game;
import com.github.syr0ws.universe.api.game.controller.GameController;
import com.github.syr0ws.universe.api.game.model.GameModel;
import com.github.syr0ws.universe.api.game.model.GameState;

public abstract class GameFinishCycle extends AbstractGameCycle {

    public GameFinishCycle(Game game, GameModel model, GameController controller) {
        super(game, model, controller);
    }

    @Override
    public GameState getGameState() {
        return GameState.FINISHED;
    }
}
