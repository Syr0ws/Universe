package com.github.syr0ws.universe.sdk.modules.combat.events;

import com.github.syr0ws.universe.sdk.modules.combat.Combat;
import org.bukkit.event.HandlerList;

public class CombatDisconnectionEvent extends CombatEvent {

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    public CombatDisconnectionEvent(Combat combat) {
        super(combat);
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }
}
