package com.github.syr0ws.universe.settings;

public interface Setting<T> {

    T getValue();

    String getName();
}
