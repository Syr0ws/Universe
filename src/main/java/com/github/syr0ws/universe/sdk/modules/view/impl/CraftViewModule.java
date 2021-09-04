package com.github.syr0ws.universe.sdk.modules.view.impl;

import com.github.syr0ws.universe.api.GamePlugin;
import com.github.syr0ws.universe.sdk.listeners.ListenerManager;
import com.github.syr0ws.universe.sdk.modules.GameModule;
import com.github.syr0ws.universe.sdk.modules.ModuleEnum;
import com.github.syr0ws.universe.sdk.modules.view.ViewModule;
import com.github.syr0ws.universe.sdk.modules.view.ViewService;
import com.github.syr0ws.universe.sdk.modules.view.handlers.ActionBarHandler;
import com.github.syr0ws.universe.sdk.modules.view.handlers.NameViewHandler;
import com.github.syr0ws.universe.sdk.modules.view.handlers.ScoreboardHandler;
import com.github.syr0ws.universe.sdk.modules.view.listeners.PlayerListener;
import com.github.syr0ws.universe.sdk.Game;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;

public class CraftViewModule extends GameModule implements ViewModule {

    public CraftViewModule(Game game) {
        super(game);
    }

    @Override
    public void load() {

    }

    @Override
    public void enable() {

        // Binding classes.
        this.bindViewService();

        // Registering handlers.
        this.registerHandlers();

        // Registering listeners.
        this.registerListeners();
    }

    @Override
    public void disable() {

        // Unregistering handlers.
        ViewService service = this.getViewService();
        service.removeViewHandlers();
    }

    @Override
    public String getName() {
        return ModuleEnum.VIEW_MODULE.getName();
    }

    @Override
    public ViewService getViewService() {
        ServicesManager manager = super.getGame().getServicesManager();
        return manager.load(ViewService.class);
    }

    private void bindViewService() {

        GamePlugin plugin = super.getGame();

        ViewService service = new CraftViewService();

        ServicesManager manager = plugin.getServicesManager();
        manager.register(ViewService.class, service, plugin, ServicePriority.Normal);
    }

    private void registerHandlers() {

        Game game = this.getGame();
        ViewService service = this.getViewService();

        service.addViewHandler(new ActionBarHandler(game));
        service.addViewHandler(new ScoreboardHandler(game));
        service.addViewHandler(new NameViewHandler(game));
    }

    private void registerListeners() {

        ListenerManager manager = super.getListenerManager();
        ViewService service = this.getViewService();

        manager.addListener(new PlayerListener(service));
    }
}
