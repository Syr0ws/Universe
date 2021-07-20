package com.github.syr0ws.universe.commons.modules.combat;

import com.github.syr0ws.universe.commons.modules.Module;

public interface CombatModule extends Module {

    CombatModel getCombatModel();

    CombatService getCombatService();
}
