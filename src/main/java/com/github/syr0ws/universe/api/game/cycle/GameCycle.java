package com.github.syr0ws.universe.api.game.cycle;

import com.github.syr0ws.universe.api.attributes.AttributeObservable;
import com.github.syr0ws.universe.api.game.model.GameState;

public interface GameCycle extends AttributeObservable {

    void load();

    void unload();

    void start();

    void stop();

    GameState getGameState();

    GameCycleState getCycleState();
}
