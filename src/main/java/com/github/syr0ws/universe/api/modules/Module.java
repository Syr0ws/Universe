package com.github.syr0ws.universe.api.modules;

public interface Module {

    void load();

    void enable();

    void disable();

    boolean isEnabled();

    String getName();
}
