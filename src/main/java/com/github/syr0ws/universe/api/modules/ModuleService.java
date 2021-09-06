package com.github.syr0ws.universe.api.modules;

import java.util.Collection;
import java.util.Optional;

public interface ModuleService {

    void registerModule(Module module) throws ModuleException;

    void unregisterModule(String name) throws ModuleException;

    void enableModule(String name) throws ModuleException;

    void disableModule(String name) throws ModuleException;

    void enableModules();

    void disableModules();

    boolean isRegistered(String name);

    Optional<Module> getModule(String name);

    <T extends Module> Optional<T> getModule(String name, Class<T> clazz);

    Collection<Module> getModules();
}
