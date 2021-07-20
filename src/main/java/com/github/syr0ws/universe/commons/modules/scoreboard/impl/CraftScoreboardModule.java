package com.github.syr0ws.universe.commons.modules.scoreboard.impl;

import com.github.syr0ws.universe.sdk.Game;
import com.github.syr0ws.universe.sdk.listeners.ListenerManager;
import com.github.syr0ws.universe.commons.modules.GameModule;
import com.github.syr0ws.universe.commons.modules.ModuleEnum;
import com.github.syr0ws.universe.commons.modules.ModuleException;
import com.github.syr0ws.universe.commons.modules.scoreboard.ScoreboardListener;
import com.github.syr0ws.universe.commons.modules.scoreboard.ScoreboardManager;
import com.github.syr0ws.universe.commons.modules.scoreboard.ScoreboardModule;

public class CraftScoreboardModule extends GameModule implements ScoreboardModule {

    private final ScoreboardManager scoreboardManager;

    public CraftScoreboardModule(Game game) {
        super(game);

        this.scoreboardManager = new CraftScoreboardManager();
    }

    @Override
    public void enable() throws ModuleException {

        ListenerManager manager = super.getListenerManager();
        manager.addListener(new ScoreboardListener(this.scoreboardManager));
    }

    @Override
    public void disable() throws ModuleException {

        super.getListenerManager().removeListeners();
    }

    @Override
    public String getName() {
        return ModuleEnum.SCOREBOARD_MODULE.getName();
    }

    @Override
    public ScoreboardManager getScoreboardManager() {
        return this.scoreboardManager;
    }
}
