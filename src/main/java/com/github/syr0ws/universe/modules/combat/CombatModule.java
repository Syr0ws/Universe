package com.github.syr0ws.universe.modules.combat;

import com.github.syr0ws.universe.modules.combat.settings.CombatSettings;

public interface CombatModule {

    CombatService getService();

    CombatSettings getSettings();
}
