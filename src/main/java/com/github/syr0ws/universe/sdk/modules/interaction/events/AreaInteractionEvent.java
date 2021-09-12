package com.github.syr0ws.universe.sdk.modules.interaction.events;

import com.github.syr0ws.universe.sdk.modules.interaction.Interaction;
import com.github.syr0ws.universe.sdk.modules.interaction.InteractiveArea;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class AreaInteractionEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    private final InteractiveArea area;
    private final Interaction<?> interaction;
    private boolean cancelled;

    public AreaInteractionEvent(InteractiveArea area, Interaction<?> interaction) {

        if(area == null)
            throw new IllegalArgumentException("InteractiveArea cannot be null.");

        if(interaction == null)
            throw new IllegalArgumentException("Interaction cannot be null.");

        this.area = area;
        this.interaction = interaction;
    }

    public InteractiveArea getArea() {
        return this.area;
    }

    public Interaction<?> getInteraction() {
        return this.interaction;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }
}
