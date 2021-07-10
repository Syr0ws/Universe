package com.github.syr0ws.universe.modules.combat.settings;

import com.github.syr0ws.universe.settings.Setting;
import com.github.syr0ws.universe.settings.SettingType;
import com.github.syr0ws.universe.settings.types.SimpleConfigSetting;

public enum CombatSettingsEnum implements SettingType {

    COMBAT_DURATION {
        @Override
        public Setting<?> getSetting() {
            return new SimpleConfigSetting
                    .Builder<>("combatDuration", 15, COMBAT_DURATION.getPath("combat-duration"), Integer.class)
                    .withFilter(value -> value > 0)
                    .build();
        }
    },

    DIE_ON_COMBAT_DISCONNECTION {
        @Override
        public Setting<?> getSetting() {
            return new SimpleConfigSetting
                    .Builder<>("dieOnCombatDisconnection", true, DIE_ON_COMBAT_DISCONNECTION.getPath("die-on-combat-disconnection"), Boolean.class)
                    .build();
        }
    };

    public static final String COMBAT_SECTION = "combat-module";

    public abstract Setting<?> getSetting();

    private String getPath(String key) {
        return COMBAT_SECTION + "." + key;
    }
}
