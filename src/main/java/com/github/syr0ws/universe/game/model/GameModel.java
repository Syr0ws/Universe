package com.github.syr0ws.universe.game.model;

import com.github.syr0ws.universe.attributes.AttributeObservable;
import com.github.syr0ws.universe.game.model.cycle.GameCycle;

public interface GameModel extends AttributeObservable {

    GameCycle getCycle();

    boolean isWaiting();

    boolean isStarted();

    boolean isFinished();
}
