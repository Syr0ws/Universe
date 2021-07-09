package com.github.syr0ws.universe.modules.scoreboard;

public interface ScoreboardCreator {

    Scoreboard create();

    ScoreboardType getType();
}
