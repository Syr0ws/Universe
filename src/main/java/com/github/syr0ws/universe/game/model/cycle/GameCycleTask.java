package com.github.syr0ws.universe.game.model.cycle;

import com.github.syr0ws.universe.Game;
import com.github.syr0ws.universe.tools.Task;

public abstract class GameCycleTask extends Task {

    private final Game game;

    public GameCycleTask(Game game) {

        if(game == null)
            throw new IllegalArgumentException("Game cannot be null.");

        this.game = game;
    }

    @Override
    public void start() {
        super.start();
        super.runTaskTimer(this.game, 0L, 20L);
    }

    public Game getGame() {
        return this.game;
    }
}
