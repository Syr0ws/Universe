package com.github.syr0ws.universe.game.controller;

import com.github.syr0ws.universe.game.model.GameException;
import com.github.syr0ws.universe.game.model.mode.Mode;
import org.bukkit.entity.Player;

public interface GameController {

    void setMode(Player player, Mode mode);

    void startGame() throws GameException;

    void stopGame() throws GameException;
}
