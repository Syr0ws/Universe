package com.github.syr0ws.universe.sdk.modules.combat.settings;

import com.github.syr0ws.universe.sdk.game.settings.types.MutableSetting;

public interface CombatSettings {

    MutableSetting<Integer> getCombatDurationSetting();

    MutableSetting<Boolean> getDieOnCombatDisconnectionSetting();
}
