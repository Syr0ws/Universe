package com.github.syr0ws.universe.game.controller;

import com.github.syr0ws.universe.game.model.GameException;
import com.github.syr0ws.universe.game.model.GamePlayer;
import com.github.syr0ws.universe.game.model.Mode;

public interface GameController {

    void setMode(GamePlayer player, Mode mode);

    void startGame() throws GameException;

    void stopGame() throws GameException;
}
