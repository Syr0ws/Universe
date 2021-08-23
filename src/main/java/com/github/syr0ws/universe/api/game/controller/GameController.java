package com.github.syr0ws.universe.api.game.controller;

import com.github.syr0ws.universe.api.game.model.GameException;
import com.github.syr0ws.universe.api.game.model.GamePlayer;
import com.github.syr0ws.universe.api.game.mode.ModeType;

public interface GameController {

    void setMode(GamePlayer player, ModeType type);

    void startGame() throws GameException;

    void stopGame() throws GameException;
}
