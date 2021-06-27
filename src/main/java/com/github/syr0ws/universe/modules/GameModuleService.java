package com.github.syr0ws.universe.modules;

import java.util.*;

public class GameModuleService implements ModuleService {

    private final List<Module> modules = new ArrayList<>();

    @Override
    public void enableModule(Module module) throws ModuleException {

        if(this.isEnabled(module.getName()))
            throw new ModuleException(String.format("Module '%s' is already enabled.", module.getName()));

        module.enable();

        this.modules.add(module);
    }

    @Override
    public void disableModule(String name) throws ModuleException {

        Optional<Module> optional = this.getModule(name);

        if(!optional.isPresent())
            throw new ModuleException(String.format("Module '%s' is not enabled.", name));

        Module module = optional.get();
        module.disable();

        this.modules.remove(module);
    }

    @Override
    public boolean isEnabled(String name) {
        return this.modules.stream()
                .anyMatch(module -> module.getName().equalsIgnoreCase(name));
    }

    @Override
    public Optional<Module> getModule(String name) {
        return this.modules.stream()
                .filter(module -> module.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    @Override
    public Collection<Module> getModules() {
        return Collections.unmodifiableList(this.modules);
    }
}
