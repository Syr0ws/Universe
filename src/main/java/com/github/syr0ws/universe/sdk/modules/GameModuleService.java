package com.github.syr0ws.universe.sdk.modules;

import com.github.syr0ws.universe.api.GamePlugin;
import com.github.syr0ws.universe.api.modules.Module;
import com.github.syr0ws.universe.api.modules.ModuleException;
import com.github.syr0ws.universe.api.modules.ModuleService;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameModuleService implements ModuleService {

    private final Logger logger;
    private final List<Module> modules = new ArrayList<>();

    public GameModuleService(GamePlugin plugin) {

        if(plugin == null)
            throw new IllegalArgumentException("GamePlugin cannot be null.");

        this.logger = plugin.getLogger();
    }

    @Override
    public void registerModule(Module module) throws ModuleException {

        if(module == null)
            throw new IllegalArgumentException("Module cannot be null.");

        String name = module.getName();

        if(name == null || name.isEmpty())
            throw new IllegalArgumentException(String.format("Invalid module name : '%s'.", name));

        if(this.isRegistered(name))
            throw new ModuleException(String.format("A module with the name '%s' is already registered.", name));

        this.modules.add(module);

        module.load();

        this.logger.log(Level.INFO, String.format("Module '%s' has been registered.", name));
    }

    @Override
    public void unregisterModule(String name) {

        // Name validation is made in the getModule(String) method.
        Optional<Module> optional = this.getModule(name);

        if(!optional.isPresent())
            throw new UnsupportedOperationException(String.format("Module '%s' is not registered.", name));

        Module module = optional.get();

        this.modules.remove(module);

        // A module can be registered but already disabled.
        // So, disabling it only if it is enabled.
        if(module.isEnabled()) module.disable();

        this.logger.log(Level.INFO, String.format("Module '%s' has been unregistered.", module.getName()));
    }

    @Override
    public void enableModule(String name) throws ModuleException {

        if(name == null || name.isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty.");

        Module module = this.getModule(name)
                .orElseThrow(() -> new ModuleException(String.format("Module '%s' not registered.", name)));

        if(module.isEnabled())
            throw new ModuleException(String.format("Module '%s' already enabled.", module.getName()));

        module.enable();

        this.logger.log(Level.INFO, String.format("Module '%s' is now enabled.", module.getName()));
    }

    @Override
    public void disableModule(String name) throws ModuleException {

        if(name == null || name.isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty.");

        Module module = this.getModule(name)
                .orElseThrow(() -> new ModuleException(String.format("Module '%s' not registered.", name)));

        if(!module.isEnabled())
            throw new ModuleException(String.format("Module '%s' already disabled.", module.getName()));

        module.disable();

        this.logger.log(Level.INFO, String.format("Module '%s' is now disabled.", module.getName()));
    }

    @Override
    public void enableModules() {

        this.modules.stream().filter(module -> !module.isEnabled()).forEach(module -> {

            try { this.enableModule(module.getName());
            } catch (ModuleException e) { e.printStackTrace(); }
        });
    }

    @Override
    public void disableModules() {

        this.modules.stream().filter(Module::isEnabled).forEach(module -> {

            try { this.disableModule(module.getName());
            } catch (ModuleException e) { e.printStackTrace(); }
        });
    }

    @Override
    public boolean isRegistered(String name) {
        return this.modules.stream()
                .anyMatch(module -> module.getName().equalsIgnoreCase(name));
    }

    @Override
    public Optional<Module> getModule(String name) {

        if(name == null || name.isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty.");

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
