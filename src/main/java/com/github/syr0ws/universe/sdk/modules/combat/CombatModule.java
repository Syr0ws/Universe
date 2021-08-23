package com.github.syr0ws.universe.sdk.modules.combat;

import com.github.syr0ws.universe.api.modules.Module;

public interface CombatModule extends Module {

    CombatModel getCombatModel();

    CombatService getCombatService();
}
