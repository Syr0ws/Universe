package com.github.syr0ws.universe.sdk.settings;

@FunctionalInterface
public interface SettingFilter<T> {

    boolean filter(T value);
}
