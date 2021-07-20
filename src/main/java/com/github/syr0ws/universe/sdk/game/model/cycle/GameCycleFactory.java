package com.github.syr0ws.universe.sdk.game.model.cycle;

import com.github.syr0ws.universe.sdk.game.model.GameState;

public interface GameCycleFactory {

    GameCycle getGameCycle(GameState state);
}
