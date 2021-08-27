package com.github.syr0ws.universe.api.settings;

public interface Setting<T> {

    T getValue();

    String getName();
}
