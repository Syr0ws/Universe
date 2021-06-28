package com.github.syr0ws.universe.game.controller;

import com.github.syr0ws.universe.game.model.GameException;

public interface GameController {

    void startGame() throws GameException;

    void stopGame() throws GameException;
}
