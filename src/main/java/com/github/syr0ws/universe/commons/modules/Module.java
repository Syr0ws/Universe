package com.github.syr0ws.universe.commons.modules;

public interface Module {

    void load();

    void enable() throws ModuleException;

    void disable() throws ModuleException;

    String getName();
}
