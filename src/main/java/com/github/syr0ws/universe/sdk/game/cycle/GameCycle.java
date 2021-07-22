package com.github.syr0ws.universe.sdk.game.cycle;

import com.github.syr0ws.universe.sdk.game.model.GameState;

public interface GameCycle {

    GameState getGameState();

    GameCycleState getCycleState();

    void load();

    void unload();

    void start();

    void stop();
}
