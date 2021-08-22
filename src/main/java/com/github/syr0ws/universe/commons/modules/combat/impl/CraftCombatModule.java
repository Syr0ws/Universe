package com.github.syr0ws.universe.commons.modules.combat.impl;

import com.github.syr0ws.universe.sdk.Game;
import com.github.syr0ws.universe.sdk.listeners.ListenerManager;
import com.github.syr0ws.universe.commons.modules.GameModule;
import com.github.syr0ws.universe.commons.modules.ModuleEnum;
import com.github.syr0ws.universe.commons.modules.combat.CombatModel;
import com.github.syr0ws.universe.commons.modules.combat.CombatModule;
import com.github.syr0ws.universe.commons.modules.combat.CombatTask;
import com.github.syr0ws.universe.commons.modules.combat.listeners.CombatListener;
import com.github.syr0ws.universe.commons.modules.combat.settings.CraftCombatSettings;

public class CraftCombatModule extends GameModule implements CombatModule {

    private final CraftCombatModel model;
    private final CraftCombatService service;
    private final CraftCombatSettings settings;
    private final CombatTask task;

    public CraftCombatModule(Game game) {
        super(game);

        this.settings = new CraftCombatSettings();
        this.model = new CraftCombatModel(this.settings);
        this.service = new CraftCombatService(this.model);
        this.task = new CombatTask(this.getGame(), this.model, this.service);
    }

    @Override
    public void load() {
        super.loadConfig();
    }

    @Override
    public void enable() {

        // Handling settings.
        this.settings.init(super.getGame().getConfig());

        // Handling listeners.
        ListenerManager listenerManager = super.getListenerManager();
        listenerManager.addListener(new CombatListener(this.model, this.service));

        // Handling task.
        this.task.start();
    }

    @Override
    public void disable() {

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
    public String getConfigName() {
        return "combat-module.yml";
    }

    @Override
    public boolean useDefaultConfig() {
        return true;
    }

    @Override
    public CombatModel getCombatModel() {
        return this.model;
    }

    @Override
    public CraftCombatService getCombatService() {
        return this.service;
    }
}
