package com.github.syr0ws.universe.modules.combat;

import com.github.syr0ws.universe.modules.Module;
import com.github.syr0ws.universe.modules.combat.settings.CombatSettings;

public interface CombatModule extends Module {

    CombatService getService();

    CombatSettings getSettings();
}
