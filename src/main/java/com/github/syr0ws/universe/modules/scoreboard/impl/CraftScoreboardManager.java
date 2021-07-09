package com.github.syr0ws.universe.modules.scoreboard.impl;

import com.github.syr0ws.universe.modules.scoreboard.Scoreboard;
import com.github.syr0ws.universe.modules.scoreboard.ScoreboardManager;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CraftScoreboardManager implements ScoreboardManager {

    private final Map<Player, Scoreboard> scoreboards = new HashMap<>();

    @Override
    public void addScoreboard(Player player, Scoreboard scoreboard) {

        if(player == null)
            throw new IllegalArgumentException("Player cannot be null.");

        if(scoreboard == null)
            throw new IllegalArgumentException("Scoreboard cannot be null.");

        if(this.scoreboards.containsKey(player))
            throw new IllegalArgumentException("Player already have a scoreboard. Remove it first.");

        this.scoreboards.put(player, scoreboard);
    }

    @Override
    public void removeScoreboard(Player player) {

        if(player == null)
            throw new IllegalArgumentException("Player cannot be null.");

        if(!this.scoreboards.containsKey(player))
            throw new IllegalArgumentException("Player doesn't have any scoreboard.");

        this.scoreboards.remove(player);
    }

    @Override
    public boolean hasScoreboard(Player player) {
        return this.scoreboards.containsKey(player);
    }

    @Override
    public Optional<? extends Scoreboard> getScoreboard(Player player) {
        return Optional.ofNullable(this.scoreboards.get(player));
    }

    @Override
    public Collection<? extends Scoreboard> getScoreboards() {
        return this.scoreboards.values();
    }
}
