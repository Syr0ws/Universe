package com.github.syr0ws.universe.api.services;

import org.bukkit.plugin.ServicesManager;

import java.util.Optional;

public interface GameServicesManager extends ServicesManager {

    <T> void register(Class<T> service, T provider, GameServicePriority priority);

    <T> T getProvider(Class<T> service);

    <T> Optional<T> getOptionalProvider(Class<T> service);
}
