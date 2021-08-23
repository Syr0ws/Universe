package com.github.syr0ws.universe.api.game.cycle;

import com.github.syr0ws.universe.api.game.model.GameState;

public interface GameCycleFactory {

    GameCycle getGameCycle(GameState state);
}
