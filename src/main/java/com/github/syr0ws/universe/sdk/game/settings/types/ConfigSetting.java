package com.github.syr0ws.universe.sdk.game.settings.types;

import com.github.syr0ws.universe.api.settings.SettingFilter;
import com.github.syr0ws.universe.sdk.game.settings.config.Configurable;
import org.bukkit.configuration.ConfigurationSection;

public abstract class ConfigSetting<T> extends MutableSetting<T> implements Configurable {

    private final String path;

    protected ConfigSetting(String name, T defaultValue, T value, SettingFilter<T> filter, String path) {
        super(name, defaultValue, value, filter);
        this.path = path;
    }

    public abstract void read(ConfigurationSection section);

    public String getPath() {
        return this.path;
    }
}
