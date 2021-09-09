package com.github.syr0ws.universe.sdk.modules.interaction;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface Interaction<T> {

    Player getPlayer();

    Location getLocation();

    InteractionType getType();

    T get();

    Class<? extends Interactive<T>> getInteractiveClass();
}
