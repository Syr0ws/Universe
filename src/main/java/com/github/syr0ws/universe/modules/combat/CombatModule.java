package com.github.syr0ws.universe.modules.combat;

import com.github.syr0ws.universe.modules.Module;

public interface CombatModule extends Module {

    CombatModel getCombatModel();

    CombatService getCombatService();
}
