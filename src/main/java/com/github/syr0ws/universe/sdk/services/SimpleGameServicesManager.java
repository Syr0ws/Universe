package com.github.syr0ws.universe.sdk.services;

import com.github.syr0ws.universe.api.GamePlugin;
import com.github.syr0ws.universe.api.services.GameServicePriority;
import com.github.syr0ws.universe.api.services.GameServicesManager;
import org.bukkit.plugin.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class SimpleGameServicesManager implements GameServicesManager {

    private final GamePlugin plugin;
    private final ServicesManager manager;

    public SimpleGameServicesManager(GamePlugin plugin) {

        if(plugin == null)
            throw new IllegalArgumentException("GamePlugin cannot ne null.");

        this.plugin = plugin;
        this.manager = plugin.getServicesManager();
    }

    @Override
    public <T> void register(Class<T> service, T provider, Plugin plugin, ServicePriority priority) {
        this.manager.register(service, provider, plugin, priority);
    }

    @Override
    public void unregisterAll(Plugin plugin) {
        this.manager.unregisterAll(plugin);
    }

    @Override
    public void unregister(Class<?> aClass, Object o) {
        this.manager.unregister(aClass, o);
    }

    @Override
    public void unregister(Object o) {
        this.manager.unregister(o);
    }

    @Override
    public <T> T load(Class<T> aClass) {
        return this.manager.load(aClass);
    }

    @Override
    public <T> RegisteredServiceProvider<T> getRegistration(Class<T> aClass) {
        return this.manager.getRegistration(aClass);
    }

    @Override
    public <T> void register(Class<T> service, T provider, GameServicePriority priority) {

        if(service == null)
            throw new IllegalArgumentException("Service cannot be null.");

        if(provider == null)
            throw new IllegalArgumentException("Provider cannot be null.");

        if(priority == null)
            throw new IllegalArgumentException("Priority cannot be null.");

        this.register(service, provider, this.plugin, priority.getServicePriority());
    }

    @Override
    public <T> T getProvider(Class<T> service) {

        T provider = this.manager.load(service);

        if(provider == null)
            throw new NullPointerException(String.format("No provider found for service '%s'.", service.getName()));

        return provider;
    }

    @Override
    public <T> Optional<T> getOptionalProvider(Class<T> service) {

        T provider = this.manager.load(service);

        return Optional.ofNullable(provider);
    }

    @Override
    public List<RegisteredServiceProvider<?>> getRegistrations(Plugin plugin) {
        return this.manager.getRegistrations(plugin);
    }

    @Override
    public <T> Collection<RegisteredServiceProvider<T>> getRegistrations(Class<T> service) {
        return this.manager.getRegistrations(service);
    }

    @Override
    public Collection<Class<?>> getKnownServices() {
        return this.manager.getKnownServices();
    }

    @Override
    public <T> boolean isProvidedFor(Class<T> aClass) {
        return this.manager.isProvidedFor(aClass);
    }
}