package com.github.syr0ws.universe.modules.combat;

import com.github.syr0ws.universe.modules.combat.impl.DefaultCombatModule;
import com.github.syr0ws.universe.modules.combat.impl.DefaultCombatService;
import com.github.syr0ws.universe.modules.combat.settings.DefaultCombatSettings;
import com.github.syr0ws.universe.tools.Task;

import java.util.ArrayList;

public class CombatTask extends Task {

    private final DefaultCombatModule module;

    public CombatTask(DefaultCombatModule module) {

        if(module == null)
            throw new IllegalArgumentException("Module cannot be null.");

        this.module = module;
    }

    @Override
    public void start() {
        super.start();
        super.runTaskTimer(this.module.getGame(), 0L, 20L);
    }

    @Override
    public void run() {

        // Variables.
        DefaultCombatService service = this.module.getService();
        DefaultCombatSettings settings = this.module.getSettings();

        // The duration of a combat is seconds.
        long duration = settings.getCombatDurationSetting().getValue();

        // Stopping expired combats.
        new ArrayList<>(service.getCombats()).stream()
                .filter(combat -> this.isExpired(combat, duration))
                .forEach(combat -> service.stopCombat(combat.getPlayer()));
    }

    private boolean isExpired(Combat combat, long duration) {
        return System.currentTimeMillis() - combat.getLastDamageTime() >= (duration * 1000);
    }
}
