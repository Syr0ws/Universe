package com.github.syr0ws.universe.api.game.controller.cycle;

import com.github.syr0ws.universe.api.attributes.AttributeObservable;
import com.github.syr0ws.universe.api.attributes.AttributeObserver;
import com.github.syr0ws.universe.api.game.controller.Controller;
import com.github.syr0ws.universe.api.game.model.GameState;

public interface GameCycle extends Controller, AttributeObservable {

    GameState getGameState();
}
