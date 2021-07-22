package com.github.syr0ws.universe.sdk.game.cycle;

import com.github.syr0ws.universe.sdk.attributes.AttributeObservable;
import com.github.syr0ws.universe.sdk.game.model.GameState;

public interface GameCycle extends AttributeObservable {

    void load();

    void unload();

    void start();

    void stop();

    GameState getGameState();

    GameCycleState getCycleState();
}
