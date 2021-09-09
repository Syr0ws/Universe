package com.github.syr0ws.universe.sdk.modules.interaction;

import com.github.syr0ws.universe.sdk.Game;
import com.github.syr0ws.universe.sdk.modules.GameModule;
import com.github.syr0ws.universe.sdk.modules.interaction.impl.CraftInteractiveAreaModel;
import com.github.syr0ws.universe.sdk.modules.interaction.listeners.PlayerInteractListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class InteractionModule extends GameModule {

    public static final String MODULE_NAME = "InteractionModule";

    private final InteractiveAreaModel model;

    public InteractionModule(Game game) {
        super(game);
        this.model = new CraftInteractiveAreaModel();
    }

    @Override
    public void enable() {
        super.enable();
        this.registerListeners();
    }

    @Override
    public String getName() {
        return MODULE_NAME;
    }

    public InteractiveAreaModel getInteractiveAreaModel() {
        return this.model;
    }

    private void registerListeners() {
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new PlayerInteractListener(this.model), super.getGame());
    }
}
