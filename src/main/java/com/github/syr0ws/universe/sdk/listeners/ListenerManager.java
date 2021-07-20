package com.github.syr0ws.universe.sdk.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ListenerManager {

    private final Plugin plugin;
    private final List<Listener> listeners;

    public ListenerManager(Plugin plugin) {

        if(plugin == null)
            throw new IllegalArgumentException("Plugin cannot be null.");

        this.plugin = plugin;
        this.listeners = new ArrayList<>();
    }

    private void registerListener(Listener listener) {
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(listener, this.plugin);
    }

    private void unregisterListener(Listener listener) {
        HandlerList.unregisterAll(listener);
    }

    public void addListener(Listener listener) {

        if(listener == null)
            throw new IllegalArgumentException("Listener cannot be null.");

        this.listeners.add(listener);
        this.registerListener(listener);
    }

    public void removeListener(Listener listener) {

        if(listener == null)
            throw new IllegalArgumentException("Listener cannot be null.");

        boolean removed = this.listeners.remove(listener);
        if(removed) this.unregisterListener(listener);
    }

    public void removeListeners() {
        this.listeners.forEach(this::unregisterListener);
        this.listeners.clear();
    }

    public Collection<Listener> getListeners() {
        return Collections.unmodifiableList(this.listeners);
    }
}
