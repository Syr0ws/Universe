package com.github.syr0ws.universe.sdk.modules.interaction.settings;

import com.github.syr0ws.universe.api.settings.SettingFilter;
import com.github.syr0ws.universe.sdk.game.settings.types.ConfigSetting;
import com.github.syr0ws.universe.sdk.modules.interaction.InteractiveData;
import com.github.syr0ws.universe.sdk.modules.interaction.config.ConfigInteractiveDataDAO;
import org.bukkit.configuration.ConfigurationSection;

public class InteractionDataSetting extends ConfigSetting<InteractiveData> {

    protected InteractionDataSetting(String name, InteractiveData defaultValue, InteractiveData value, SettingFilter<InteractiveData> filter, String path) {
        super(name, defaultValue, value, filter, path);
    }

    @Override
    public void read(ConfigurationSection section) {

        ConfigInteractiveDataDAO dao = new ConfigInteractiveDataDAO(section);
        InteractiveData data = dao.load(super.getName());

        super.setValue(data);
    }
}
