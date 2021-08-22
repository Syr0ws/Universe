package com.github.syr0ws.universe.commons.modules;

public interface Module {

    void load();

    void enable();

    void disable();

    String getName();
}
