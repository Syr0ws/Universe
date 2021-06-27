package com.github.syr0ws.universe.modules;

public interface Module {

    void enable() throws ModuleException;

    void disable() throws ModuleException;

    String getName();
}
