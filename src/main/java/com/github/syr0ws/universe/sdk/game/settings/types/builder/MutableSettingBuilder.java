package com.github.syr0ws.universe.sdk.game.settings.types.builder;

import com.github.syr0ws.universe.api.settings.SettingFilter;
import com.github.syr0ws.universe.sdk.game.settings.types.MutableSetting;

public abstract class MutableSettingBuilder<T, S extends MutableSetting<T>, B extends MutableSettingBuilder<T, S, B>> {

    private final String name;
    private final T defaultValue;

    private T value;
    private SettingFilter<T> filter;

    public MutableSettingBuilder(String name, T defaultValue) {

        if(name == null || name.isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty.");

        this.name = name;
        this.defaultValue = defaultValue;
    }

    public abstract B self();

    public abstract S build();

    public B withValue(T value) {
        this.value = value;
        return this.self();
    }

    public B withFilter(SettingFilter<T> filter) {
        this.filter = filter;
        return this.self();
    }

    public String getName() {
        return this.name;
    }

    public T getDefaultValue() {
        return this.defaultValue;
    }

    public T getValue() {
        return this.value;
    }

    public SettingFilter<T> getFilter() {
        return this.filter;
    }
}
