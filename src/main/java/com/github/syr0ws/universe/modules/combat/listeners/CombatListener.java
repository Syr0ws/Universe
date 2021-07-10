package com.github.syr0ws.universe.modules.combat.listeners;

import com.github.syr0ws.universe.modules.combat.Combat;
import com.github.syr0ws.universe.modules.combat.CombatModel;
import com.github.syr0ws.universe.modules.combat.CombatService;
import com.github.syr0ws.universe.modules.combat.Hit;
import com.github.syr0ws.universe.modules.combat.events.CombatDisconnectionEvent;
import com.github.syr0ws.universe.modules.combat.impl.CraftCombat;
import com.github.syr0ws.universe.settings.types.MutableSetting;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class CombatListener implements Listener {

    private final CombatModel model;
    private final CombatService service;

    public CombatListener(CombatModel model, CombatService service) {

        if(model == null)
            throw new IllegalArgumentException("CombatModel cannot be null.");

        if(service == null)
            throw new IllegalArgumentException("CombatService cannot be null.");

        this.model = model;
        this.service = service;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerQuit(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        if(!this.model.isInCombat(player)) return;

        // Stopping combat for this player.
        Combat combat = this.service.stopCombat(player);

        // Throwing an event.
        CombatDisconnectionEvent combatDisconnectionEvent = new CombatDisconnectionEvent(combat);
        Bukkit.getPluginManager().callEvent(combatDisconnectionEvent);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onCombatDisconnection(CombatDisconnectionEvent event) {

        Player player = event.getPlayer();

        MutableSetting<Boolean> setting = this.model.getSettings().getDieOnCombatDisconnectionSetting();

        // If the setting value is set to 'true', players have to be killed
        // when disconnecting in combat.
        if(setting.getValue()) this.service.die(player);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerDamagePlayer(EntityDamageByEntityEvent event) {

        if(event.isCancelled()) return;

        Entity damaged = event.getEntity(), damager = event.getDamager();

        if(!this.isPlayer(damaged) || !this.isPlayer(damager)) return;

        Player damagedPlayer = (Player) damaged;
        Player damagerPlayer = (Player) damager;

        double damages = event.getFinalDamage();
        long time = System.currentTimeMillis();

        Hit hit = new Hit(damagerPlayer, damages, time);

        this.service.onHit(damagedPlayer, hit);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerDamage(EntityDamageEvent event) {

        // If the event is cancelled, no damage is applied.
        if(event.isCancelled()) return;

        Entity damaged = event.getEntity();

        // Only players are interesting us.
        if(!(damaged instanceof Player)) return;

        Player player = (Player) damaged;

        // Final damage includes damage reduction.
        double damages = event.getFinalDamage();
        double health = player.getHealth();

        // If damages >= health, killing the player.
        if(damages >= health) this.service.die(player);
    }

    private boolean isPlayer(Entity entity) {
        return entity instanceof Player;
    }
}
