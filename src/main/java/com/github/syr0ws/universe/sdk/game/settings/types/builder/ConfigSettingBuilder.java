package com.github.syr0ws.universe.sdk.game.settings.types.builder;

import com.github.syr0ws.universe.sdk.game.settings.types.ConfigSetting;

public abstract class ConfigSettingBuilder<T, S extends ConfigSetting<T>, B extends MutableSettingBuilder<T, S, B>> extends MutableSettingBuilder<T, S, B> {

    private final String path;

    public ConfigSettingBuilder(String name, T defaultValue, String path) {
        super(name, defaultValue);

        if(path == null)
            throw new IllegalArgumentException("Path cannot be null.");

        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}
