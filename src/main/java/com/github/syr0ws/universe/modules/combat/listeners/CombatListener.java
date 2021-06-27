package com.github.syr0ws.universe.modules.combat.listeners;

import com.github.syr0ws.universe.modules.combat.CombatService;
import com.github.syr0ws.universe.modules.combat.Hit;
import com.github.syr0ws.universe.modules.combat.events.CombatDisconnectionEvent;
import com.github.syr0ws.universe.modules.combat.impl.PlayerCombat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class CombatListener implements Listener {

    private final CombatService service;

    public CombatListener(CombatService service) {
        this.service = service;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerQuit(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        if(!this.service.isInCombat(player)) return;

        // Stopping combat for this player.
        PlayerCombat combat = this.service.stopCombat(player);

        // Throwing an event.
        CombatDisconnectionEvent combatDisconnectionEvent = new CombatDisconnectionEvent(combat);
        Bukkit.getPluginManager().callEvent(combatDisconnectionEvent);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerDamagePlayer(EntityDamageByEntityEvent event) {

        // if(event.isCancelled()) return;

        Entity damaged = event.getEntity(), damager = event.getDamager();

        if(!this.isPlayer(damaged) || !this.isPlayer(damager)) return;

        Player damagedPlayer = (Player) damaged;
        Player damagerPlayer = (Player) damager;

        double damages = event.getFinalDamage();
        long time = System.currentTimeMillis();

        Hit hit = new Hit(damagerPlayer, damages, time);

        this.service.onHit(damagedPlayer, hit);
    }

    private boolean isPlayer(Entity entity) {
        return entity instanceof Player;
    }
}
