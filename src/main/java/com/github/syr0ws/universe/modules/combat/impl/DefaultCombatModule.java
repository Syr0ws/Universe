package com.github.syr0ws.universe.modules.combat.impl;

import com.github.syr0ws.universe.Game;
import com.github.syr0ws.universe.listeners.ListenerManager;
import com.github.syr0ws.universe.modules.GameModule;
import com.github.syr0ws.universe.modules.ModuleEnum;
import com.github.syr0ws.universe.modules.ModuleException;
import com.github.syr0ws.universe.modules.combat.CombatModule;
import com.github.syr0ws.universe.modules.combat.CombatTask;
import com.github.syr0ws.universe.modules.combat.listeners.CombatListener;
import com.github.syr0ws.universe.modules.combat.settings.DefaultCombatSettings;

public class DefaultCombatModule extends GameModule implements CombatModule {

    private final DefaultCombatService service;
    private final DefaultCombatSettings settings;
    private final CombatTask task;

    public DefaultCombatModule(Game game) {
        super(game);

        this.service = new DefaultCombatService();
        this.settings = new DefaultCombatSettings();
        this.task = new CombatTask(this);
    }

    @Override
    public void enable() throws ModuleException {

        // Handling settings.
        this.settings.init(super.getGame().getConfig());

        // Handling listeners.
        ListenerManager listenerManager = super.getListenerManager();
        listenerManager.addListener(new CombatListener(this.service));

        // Handling task.
        this.task.start();
    }

    @Override
    public void disable() throws ModuleException {

        // Handling listeners.
        ListenerManager listenerManager = super.getListenerManager();
        listenerManager.removeListeners();

        // Handling task.
        this.task.stop();
    }

    @Override
    public String getName() {
        return ModuleEnum.COMBAT_MODULE.getName();
    }

    @Override
    public DefaultCombatService getService() {
        return this.service;
    }

    @Override
    public DefaultCombatSettings getSettings() {
        return this.settings;
    }
}
