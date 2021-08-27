package com.github.syr0ws.universe.api.settings;

public interface MutableSetting<T> extends Setting<T> {

    T getDefaultValue();

    SettingFilter<T> getFilter();
}
