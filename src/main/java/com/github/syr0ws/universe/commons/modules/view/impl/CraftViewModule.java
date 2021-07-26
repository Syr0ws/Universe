package com.github.syr0ws.universe.commons.modules.view.impl;

import com.github.syr0ws.universe.commons.modules.GameModule;
import com.github.syr0ws.universe.commons.modules.ModuleEnum;
import com.github.syr0ws.universe.commons.modules.ModuleException;
import com.github.syr0ws.universe.commons.modules.view.ViewModule;
import com.github.syr0ws.universe.commons.modules.view.ViewService;
import com.github.syr0ws.universe.commons.modules.view.handlers.ActionBarHandler;
import com.github.syr0ws.universe.commons.modules.view.handlers.ScoreboardHandler;
import com.github.syr0ws.universe.commons.modules.view.listeners.PlayerListener;
import com.github.syr0ws.universe.sdk.Game;

public class CraftViewModule extends GameModule implements ViewModule {

    private final ViewService service;

    public CraftViewModule(Game game) {
        super(game);
        this.service = new CraftViewService();
    }

    @Override
    public void enable() throws ModuleException {

        // Registering handlers.
        this.registerHandlers();

        // Registering listeners.
        super.getListenerManager().addListener(new PlayerListener(this.service));
    }

    @Override
    public void disable() throws ModuleException {

        // Unregistering handlers.
        this.service.removeViewHandlers();
    }

    @Override
    public String getName() {
        return ModuleEnum.VIEW_MODULE.getName();
    }

    @Override
    public ViewService getViewService() {
        return this.service;
    }

    private void registerHandlers() {

        Game game = this.getGame();

        this.service.addViewHandler(new ActionBarHandler(game));
        this.service.addViewHandler(new ScoreboardHandler(game));
    }
}
