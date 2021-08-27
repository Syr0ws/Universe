package com.github.syr0ws.universe.api.settings;

@FunctionalInterface
public interface SettingFilter<T> {

    boolean filter(T value);
}
