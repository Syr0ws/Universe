package com.github.syr0ws.universe.api.game.controller.cycle;

import com.github.syr0ws.universe.api.game.model.GameState;

public interface GameCycleFactory {

    GameCycle getGameCycle(GameState state);
}
