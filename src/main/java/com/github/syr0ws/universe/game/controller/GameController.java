package com.github.syr0ws.universe.game.controller;

import com.github.syr0ws.universe.game.model.GameException;
import com.github.syr0ws.universe.game.model.GameModel;

public interface GameController {

    void startGame() throws GameException;

    void stopGame() throws GameException;

    GameModel getGameModel();
}
