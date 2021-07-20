package com.github.syr0ws.universe.commons.modules.scoreboard.impl;

import com.github.syr0ws.universe.commons.modules.scoreboard.Scoreboard;
import com.github.syr0ws.universe.commons.modules.scoreboard.ScoreboardManager;
import org.bukkit.entity.Player;

public abstract class AbstractScoreboard implements Scoreboard {

    private final ScoreboardManager manager;
    private final Player player;

    public AbstractScoreboard(ScoreboardManager manager, Player player) {

        if(manager == null)
            throw new IllegalArgumentException("ScoreboardManager cannot be null.");

        if(player == null)
            throw new IllegalArgumentException("Player cannot be null.");

        this.manager = manager;
        this.player = player;
    }

    @Override
    public void set() {
        this.manager.addScoreboard(this.player, this);
    }

    @Override
    public void remove() {
        this.manager.removeScoreboard(this.player);
    }

    public Player getPlayer() {
        return this.player;
    }
}
