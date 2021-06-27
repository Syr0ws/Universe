package com.github.syr0ws.universe.settings;

@FunctionalInterface
public interface SettingFilter<T> {

    boolean filter(T value);
}
