package com.github.syr0ws.universe.commons.modules.combat.settings;

import com.github.syr0ws.universe.sdk.settings.types.MutableSetting;

public interface CombatSettings {

    MutableSetting<Integer> getCombatDurationSetting();

    MutableSetting<Boolean> getDieOnCombatDisconnectionSetting();
}
