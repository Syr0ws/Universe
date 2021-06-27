package com.github.syr0ws.universe.modules.combat.settings;

import com.github.syr0ws.universe.settings.dao.ConfigSettingLoader;
import com.github.syr0ws.universe.settings.dao.SettingLoader;
import com.github.syr0ws.universe.settings.manager.CacheSettingManager;
import com.github.syr0ws.universe.settings.manager.SettingManager;
import com.github.syr0ws.universe.settings.types.MutableSetting;
import org.bukkit.configuration.file.FileConfiguration;

public class DefaultCombatSettings implements CombatSettings {

    private final SettingManager manager;

    public DefaultCombatSettings() {
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
}
