package com.github.syr0ws.universe.commons.modules.combat.settings;

import com.github.syr0ws.universe.sdk.settings.dao.ConfigSettingLoader;
import com.github.syr0ws.universe.sdk.settings.dao.SettingLoader;
import com.github.syr0ws.universe.sdk.settings.manager.CacheSettingManager;
import com.github.syr0ws.universe.sdk.settings.manager.SettingManager;
import com.github.syr0ws.universe.sdk.settings.types.MutableSetting;
import org.bukkit.configuration.file.FileConfiguration;

public class CraftCombatSettings implements CombatSettings {

    private final SettingManager manager;

    public CraftCombatSettings() {
        this.manager = new CacheSettingManager();
    }

    public void init(FileConfiguration config) {

        for (CombatSettingsEnum value : CombatSettingsEnum.values()) {
            this.manager.addSetting(value, value.getSetting());
        }

        SettingLoader loader = new ConfigSettingLoader(config);
        loader.load(this.manager.getSettings());
    }

    @Override
    public MutableSetting<Integer> getCombatDurationSetting() {
        return this.manager.getGenericSetting(CombatSettingsEnum.COMBAT_DURATION, Integer.class);
    }

    @Override
    public MutableSetting<Boolean> getDieOnCombatDisconnectionSetting() {
        return this.manager.getGenericSetting(CombatSettingsEnum.DIE_ON_COMBAT_DISCONNECTION, Boolean.class);
    }
}
