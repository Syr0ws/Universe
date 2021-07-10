package com.github.syr0ws.universe.modules.combat.settings;

import com.github.syr0ws.universe.settings.types.MutableSetting;

public interface CombatSettings {

    MutableSetting<Integer> getCombatDurationSetting();

    MutableSetting<Boolean> getDieOnCombatDisconnectionSetting();
}
