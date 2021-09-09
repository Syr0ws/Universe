package com.github.syr0ws.universe.sdk.modules.interaction.impl;

import com.github.syr0ws.universe.sdk.modules.interaction.*;
import org.bukkit.Location;

import java.util.Optional;

public class GlobalArea implements InteractiveArea {

    private final InteractiveData data;

    public GlobalArea(InteractiveData data) {

        if(data == null)
            throw new IllegalArgumentException("InteractiveData cannot be null.");

        this.data = data;
    }

    @Override
    public boolean isIn(Location location) {
        return true;
    }

    @Override
    public boolean isAllowed(Interaction<?> interaction) {
        Optional<? extends Interactive<?>> optional = this.data.getInteractive(interaction);
        return !optional.isPresent() || optional.get().isInteractive();
    }

    @Override
    public InteractiveAreaPriority getPriority() {
        return InteractiveAreaPriority.LOWEST;
    }
}
