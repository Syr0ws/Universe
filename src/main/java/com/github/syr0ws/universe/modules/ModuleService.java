package com.github.syr0ws.universe.modules;

import java.util.Collection;
import java.util.Optional;

public interface ModuleService {

    void enableModule(Module module);

    void disableModule(String name);

    void disableModules();

    boolean isEnabled(String name);

    Optional<Module> getModule(String name);

    Collection<Module> getModules();
}
