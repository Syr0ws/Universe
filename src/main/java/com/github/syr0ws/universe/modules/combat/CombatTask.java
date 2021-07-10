package com.github.syr0ws.universe.modules.combat;

import com.github.syr0ws.universe.Game;
import com.github.syr0ws.universe.modules.combat.impl.CraftCombatModule;
import com.github.syr0ws.universe.modules.combat.impl.CraftCombatService;
import com.github.syr0ws.universe.modules.combat.settings.CombatSettings;
import com.github.syr0ws.universe.modules.combat.settings.CraftCombatSettings;
import com.github.syr0ws.universe.tools.Task;

import java.util.ArrayList;

public class CombatTask extends Task {

    private final Game game;
    private final CombatModel model;
    private final CombatService service;

    public CombatTask(Game game, CombatModel model, CombatService service) {

        if(game == null)
            throw new IllegalArgumentException("Game cannot be null.");

        if(model == null)
            throw new IllegalArgumentException("CombatModel cannot be null.");

        if(service == null)
            throw new IllegalArgumentException("CombatService cannot be null.");

        this.game = game;
        this.model = model;
        this.service = service;
    }

    @Override
    public void start() {
        super.start();
        super.runTaskTimer(this.game, 0L, 20L);
    }

    @Override
    public void run() {

        // Retrieving settings.
        CombatSettings settings = this.model.getSettings();

        // The duration of a combat is seconds.
        long duration = settings.getCombatDurationSetting().getValue();

        // Stopping expired combats.
        new ArrayList<>(this.model.getCombats()).stream()
                .filter(combat -> this.isExpired(combat, duration))
                .forEach(combat -> service.stopCombat(combat.getPlayer()));
    }

    private boolean isExpired(Combat combat, long duration) {
        return System.currentTimeMillis() - combat.getLastDamageTime() >= (duration * 1000);
    }
}
