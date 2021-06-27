package com.github.syr0ws.universe;

import com.github.syr0ws.universe.listeners.ListenerManager;
import com.github.syr0ws.universe.modules.GameModuleService;
import com.github.syr0ws.universe.modules.ModuleService;
import org.bukkit.plugin.java.JavaPlugin;

public class Game extends JavaPlugin {

    private final ModuleService service;
    private final ListenerManager listenerManager;

    public Game() {
        this.service = new GameModuleService();
        this.listenerManager = new ListenerManager(this);
    }

    public ListenerManager getListenerManager() {
        return this.listenerManager;
    }

    public ModuleService getModuleService() {
        return this.service;
    }
}
