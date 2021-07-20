package com.github.syr0ws.universe.sdk.game.model.cycle;

import com.github.syr0ws.universe.sdk.game.model.GameState;
import com.github.syr0ws.universe.sdk.Game;
import com.github.syr0ws.universe.sdk.attributes.AbstractAttributeObservable;
import com.github.syr0ws.universe.sdk.attributes.Attribute;
import com.github.syr0ws.universe.sdk.listeners.ListenerManager;

public interface GameCycle {

    GameState getGameState();

    GameCycleState getCycleState();

    void load();

    void unload();

    void start();

    void stop();
}
