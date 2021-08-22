package com.github.syr0ws.universe.commons.modules;

import java.util.*;

public class GameModuleService implements ModuleService {

    private final List<Module> modules = new ArrayList<>();

    @Override
    public void registerModule(Module module)  {

        if(this.isEnabled(module.getName()))
            throw new UnsupportedOperationException(String.format("Module '%s' is already enabled.", module.getName()));

        this.modules.add(module);

        module.load();
    }

    @Override
    public void unregisterModule(String name) {

        Optional<Module> optional = this.getModule(name);

        if(!optional.isPresent())
            throw new UnsupportedOperationException(String.format("Module '%s' is not enabled.", name));

        Module module = optional.get();

        this.modules.remove(module);

        module.disable();
    }

    @Override
    public void enableModules() {
        this.modules.forEach(Module::enable);
    }

    @Override
    public void disableModules() {
        this.modules.forEach(module -> this.unregisterModule(module.getName()));
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
    public <T extends Module> Optional<T> getModule(String name, Class<T> clazz) {
        return this.modules.stream()
                .filter(clazz::isInstance)
                .map(clazz::cast)
                .findFirst();
    }

    @Override
    public Collection<Module> getModules() {
        return Collections.unmodifiableList(this.modules);
    }
}
