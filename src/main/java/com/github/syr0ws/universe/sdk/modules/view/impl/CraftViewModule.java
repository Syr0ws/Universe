package com.github.syr0ws.universe.sdk.modules.view.impl;

import com.github.syr0ws.universe.sdk.modules.GameModule;
import com.github.syr0ws.universe.sdk.modules.ModuleEnum;
import com.github.syr0ws.universe.sdk.modules.view.ViewModule;
import com.github.syr0ws.universe.sdk.modules.view.ViewService;
import com.github.syr0ws.universe.sdk.modules.view.handlers.ActionBarHandler;
import com.github.syr0ws.universe.sdk.modules.view.handlers.NameViewHandler;
import com.github.syr0ws.universe.sdk.modules.view.handlers.ScoreboardHandler;
import com.github.syr0ws.universe.sdk.modules.view.listeners.PlayerListener;
import com.github.syr0ws.universe.sdk.Game;

public class CraftViewModule extends GameModule implements ViewModule {

    private final ViewService service;

    public static final String MODULE_NAME = "ViewModule";

    public CraftViewModule(Game game) {
        super(game);
        this.service = new CraftViewService();
    }

    @Override
    public void enable() {
        super.enable();

        // Registering handlers.
        this.registerHandlers();

        // Registering listeners.
        super.getListenerManager().addListener(new PlayerListener(this.service));
    }

    @Override
    public void disable() {
        super.disable();

        // Unregistering handlers.
        this.service.removeViewHandlers();
    }

    @Override
    public String getName() {
        return MODULE_NAME;
    }

    @Override
    public ViewService getViewService() {
        return this.service;
    }

    private void registerHandlers() {

        Game game = this.getGame();

        this.service.addViewHandler(new ActionBarHandler(game));
        this.service.addViewHandler(new ScoreboardHandler(game));
        this.service.addViewHandler(new NameViewHandler(game));
    }
}
