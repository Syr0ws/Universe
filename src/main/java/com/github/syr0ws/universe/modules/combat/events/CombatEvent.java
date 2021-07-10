package com.github.syr0ws.universe.modules.combat.events;

import com.github.syr0ws.universe.modules.combat.Combat;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public abstract class CombatEvent extends Event {

    private final Combat combat;

    public CombatEvent(Combat combat) {

        if(combat == null)
            throw new IllegalArgumentException("CraftCombat cannot be null.");

        this.combat = combat;
    }

    public Player getPlayer() {
        return this.combat.getPlayer();
    }

    public Combat getCombat() {
        return this.combat;
    }
}
