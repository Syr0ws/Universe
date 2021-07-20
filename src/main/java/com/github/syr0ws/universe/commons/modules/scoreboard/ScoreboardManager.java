package com.github.syr0ws.universe.commons.modules.scoreboard;

import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Optional;

public interface ScoreboardManager {

    void addScoreboard(Player player, Scoreboard scoreboard);

    void removeScoreboard(Player player);

    boolean hasScoreboard(Player player);

    Optional<? extends Scoreboard> getScoreboard(Player player);

    Collection<? extends Scoreboard> getScoreboards();
}
