package com.github.syr0ws.universe.sdk.modules.interaction.settings;

import com.github.syr0ws.universe.api.settings.SettingFilter;
import com.github.syr0ws.universe.sdk.game.settings.types.ConfigSetting;
import com.github.syr0ws.universe.sdk.game.settings.types.builder.ConfigSettingBuilder;
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

    public static class Builder extends ConfigSettingBuilder<InteractiveData, InteractionDataSetting, Builder> {

        public Builder(String name, InteractiveData defaultValue, String path) {
            super(name, defaultValue, path);
        }

        @Override
        public Builder self() {
            return this;
        }

        @Override
        public InteractionDataSetting build() {
            return new InteractionDataSetting(super.getName(), super.getDefaultValue(), super.getValue(), super.getFilter(), super.getPath());
        }
    }
}
