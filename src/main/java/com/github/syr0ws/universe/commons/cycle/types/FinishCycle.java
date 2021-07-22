package com.github.syr0ws.universe.commons.cycle.types;

import com.github.syr0ws.universe.commons.cycle.DefaultGameCycle;
import com.github.syr0ws.universe.sdk.Game;
import com.github.syr0ws.universe.sdk.game.controller.GameController;
import com.github.syr0ws.universe.sdk.game.model.GameModel;
import com.github.syr0ws.universe.sdk.game.model.GameState;

public class FinishCycle extends DefaultGameCycle {

    public FinishCycle(Game game, GameModel model, GameController controller) {
        super(game, model, controller);
    }

    @Override
    public GameState getGameState() {
        return GameState.FINISHED;
    }
}
