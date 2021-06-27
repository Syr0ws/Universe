package com.github.syr0ws.universe;

import com.github.syr0ws.universe.modules.combat.events.CombatDisconnectionEvent;
import com.github.syr0ws.universe.modules.combat.events.CombatStartEvent;
import com.github.syr0ws.universe.modules.combat.events.CombatStopEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class UniverseListener implements Listener {

    @EventHandler
    public void onCombatStart(CombatStartEvent event) {
        System.out.println("combat started for " + event.getPlayer().getName());
    }

    @EventHandler
    public void onCombatStop(CombatStopEvent event) {
        System.out.println("combat stopped for " + event.getPlayer().getName());
    }

    @EventHandler
    public void onCombatDisconnection(CombatDisconnectionEvent event) {
        System.out.println("combat disconnection for " + event.getPlayer().getName());
    }
}
