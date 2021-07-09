package com.github.syr0ws.universe.modules.scoreboard.impl;

import com.github.syr0ws.universe.Game;
import com.github.syr0ws.universe.listeners.ListenerManager;
import com.github.syr0ws.universe.modules.GameModule;
import com.github.syr0ws.universe.modules.ModuleEnum;
import com.github.syr0ws.universe.modules.ModuleException;
import com.github.syr0ws.universe.modules.scoreboard.ScoreboardCreatorManager;
import com.github.syr0ws.universe.modules.scoreboard.ScoreboardListener;
import com.github.syr0ws.universe.modules.scoreboard.ScoreboardManager;
import com.github.syr0ws.universe.modules.scoreboard.ScoreboardModule;

public class CraftScoreboardModule extends GameModule implements ScoreboardModule {

    private final ScoreboardManager scoreboardManager;
    private final ScoreboardCreatorManager creatorManager;

    public CraftScoreboardModule(Game game) {
        super(game);

        this.scoreboardManager = new CraftScoreboardManager();
        this.creatorManager = new CraftScoreboardCreatorManager();
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
    public ScoreboardManager getScoreboardModel() {
        return this.scoreboardManager;
    }

    @Override
    public ScoreboardCreatorManager getScoreboardCreatorManager() {
        return this.creatorManager;
    }
}
