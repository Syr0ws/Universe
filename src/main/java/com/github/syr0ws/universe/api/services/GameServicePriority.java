package com.github.syr0ws.universe.api.services;

import org.bukkit.plugin.ServicePriority;

public enum GameServicePriority {

    LOWEST(ServicePriority.Lowest),
    LOW(ServicePriority.Low),
    NORMAL(ServicePriority.Normal),
    HIGH(ServicePriority.High),
    HIGHEST(ServicePriority.Highest);

    private final ServicePriority priority;

    GameServicePriority(ServicePriority priority) {
        this.priority = priority;
    }

    public ServicePriority getServicePriority() {
        return this.priority;
    }
}
