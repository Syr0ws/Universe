package com.github.syr0ws.universe.sdk.modules.interaction.impl;

import com.github.syr0ws.universe.sdk.modules.interaction.Interaction;
import com.github.syr0ws.universe.sdk.modules.interaction.InteractionType;
import com.github.syr0ws.universe.sdk.modules.interaction.Interactive;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class CraftInteraction<T> implements Interaction<T> {

    private final Player player;
    private final Location location;
    private final InteractionType type;
    private final T element;
    private final Class<? extends Interactive<T>> clazz;

    public CraftInteraction(Player player, Location location, InteractionType type, T element, Class<? extends Interactive<T>> clazz) {
        this.player = player;
        this.location = location;
        this.type = type;
        this.element = element;
        this.clazz = clazz;
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    @Override
    public InteractionType getType() {
        return this.type;
    }

    @Override
    public T get() {
        return this.element;
    }

    @Override
    public Class<? extends Interactive<T>> getInteractiveClass() {
        return this.clazz;
    }
}
