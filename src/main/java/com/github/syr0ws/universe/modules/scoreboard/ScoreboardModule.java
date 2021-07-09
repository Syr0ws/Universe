package com.github.syr0ws.universe.modules.scoreboard;

import com.github.syr0ws.universe.modules.Module;

public interface ScoreboardModule extends Module {

    ScoreboardManager getScoreboardManager();

    ScoreboardCreatorManager getScoreboardCreatorManager();
}
