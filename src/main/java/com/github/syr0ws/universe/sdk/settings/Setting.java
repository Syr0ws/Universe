package com.github.syr0ws.universe.sdk.settings;

public interface Setting<T> {

    T getValue();

    String getName();
}
