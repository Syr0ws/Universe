package com.github.syr0ws.universe.sdk.modules.interaction;

import org.bukkit.Location;

public interface InteractiveArea {

    boolean isIn(Location location);

    boolean isAllowed(Interaction<?> interaction);

    InteractiveAreaPriority getPriority();
}
