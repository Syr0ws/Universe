package com.github.syr0ws.universe.sdk.game.settings.types;

import com.github.syr0ws.universe.api.settings.Setting;

public abstract class AbstractSetting<T> implements Setting<T> {

    private final String name;

    public AbstractSetting(String name) {

        if(name == null)
            throw new IllegalArgumentException("Name cannot be null.");

        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
