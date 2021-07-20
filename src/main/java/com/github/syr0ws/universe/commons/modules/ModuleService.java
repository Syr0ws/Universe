package com.github.syr0ws.universe.commons.modules;

import java.util.Collection;
import java.util.Optional;

public interface ModuleService {

    void enableModule(Module module);

    void disableModule(String name);

    void disableModules();

    boolean isEnabled(String name);

    Optional<Module> getModule(String name);

    <T extends Module> Optional<T> getModule(String name, Class<T> clazz);

    Collection<Module> getModules();
}
