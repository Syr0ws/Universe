package com.github.syr0ws.universe.modules;

import java.util.Collection;
import java.util.Optional;

public interface ModuleService {

    void enableModule(Module module) throws ModuleException;

    void disableModule(String name) throws ModuleException;

    boolean isEnabled(String name);

    Optional<Module> getModule(String name);

    Collection<Module> getModules();
}
