package com.github.syr0ws.universe.commons.modules.combat.impl;

import com.github.syr0ws.universe.commons.modules.combat.Combat;
import com.github.syr0ws.universe.commons.modules.combat.CombatException;
import com.github.syr0ws.universe.commons.modules.combat.CombatService;
import com.github.syr0ws.universe.commons.modules.combat.Hit;
import com.github.syr0ws.universe.commons.modules.combat.events.CombatStartEvent;
import com.github.syr0ws.universe.commons.modules.combat.events.CombatStopEvent;
import com.github.syr0ws.universe.commons.modules.combat.events.GamePlayerDeathEvent;
import com.github.syr0ws.universe.commons.modules.combat.events.GamePlayerRespawnEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;

public class CraftCombatService implements CombatService {

    private final CraftCombatModel model;

    public CraftCombatService(CraftCombatModel model) {
        this.model = model;
    }

    private void setInCombat(Player player) {

        Combat combat = this.model.setInCombat(player);

        CombatStartEvent event = new CombatStartEvent(combat);
        Bukkit.getPluginManager().callEvent(event);
    }

    @Override
    public void die(Player player) {

        // If the player is in combat, stopping it.
        // A player isn't necessary in combat as death can be due to several causes.
        if(this.model.isInCombat(player)) this.stopCombat(player);

        // Throwing an event.
        GamePlayerDeathEvent event = new GamePlayerDeathEvent(player);
        Bukkit.getPluginManager().callEvent(event);

        // Handling event.
        this.handleInventory(event);
        this.handlePotionEffects(event);
        this.handleExp(event);
        this.handleDrops(event);

        // Handling player.
        player.setHealth(player.getMaxHealth());
        player.setFoodLevel(20);

        // Handling respawn.
        this.handleRespawn(player);
    }

    @Override
    public void onHit(Player player, Hit hit) {

        if(!this.model.isInCombat(player)) this.setInCombat(player);

        if(!this.model.isInCombat(hit.getDamager())) this.setInCombat(hit.getDamager());

        // Victim combat.
        CraftCombat victimCombat = this.model.getCombat(player).get();
        victimCombat.addHit(hit); // Adding hit.
        victimCombat.setLastDamageTime(System.currentTimeMillis()); // Updating last damage.

        // Damager combat.
        CraftCombat damagerCombat = this.model.getCombat(player).get();
        // Not hit to add because hits are only for the player who receives them.
        damagerCombat.setLastDamageTime(System.currentTimeMillis()); // Updating last damage.
    }

    @Override
    public CraftCombat stopCombat(Player player) {

        if(!this.model.isInCombat(player))
            throw new CombatException("Player not in combat.");

        CraftCombat combat = this.model.removeCombat(player);

        CombatStopEvent event = new CombatStopEvent(combat);
        Bukkit.getPluginManager().callEvent(event);

        return combat;
    }

    @Override
    public void stopCombats() {
        this.model.getPlayersInCombat().forEach(this::stopCombat);
    }

    private void handleRespawn(Player player) {

        // Throwing an event.
        GamePlayerRespawnEvent event = new GamePlayerRespawnEvent(player);
        Bukkit.getPluginManager().callEvent(event);

        Location respawn = event.getRespawnLocation();

        player.teleport(respawn);
    }

    private void handleInventory(GamePlayerDeathEvent event) {

        Player player = event.getPlayer();

        // If player's inventory is kept, don't do anything else.
        if(event.getKeepInventory()) return;

        player.getInventory().clear();
    }

    private void handlePotionEffects(GamePlayerDeathEvent event) {

        Player player = event.getPlayer();

        // If player's potion effects are kept, don't do anything else.
        if(event.getKeepPotionEffects()) return;

        // Removing player's potion effects.
        player.getActivePotionEffects()
                .forEach(effect -> player.removePotionEffect(effect.getType()));
    }

    private void handleExp(GamePlayerDeathEvent event) {

        Player player = event.getPlayer();

        // If player's experience is kept, don't do anything else.
        if(event.getKeepExp()) return;

        // Removing player's experience and levels.
        player.setExp(0);
        player.setLevel(0);
    }

    private void handleDrops(GamePlayerDeathEvent event) {

        Player player = event.getPlayer();

        Location location = player.getLocation();
        World world = location.getWorld();

        // Handling item drop.
        event.getDrops().stream()
                .filter(drop -> drop != null && drop.getType() != Material.AIR)
                .forEach(drop -> world.dropItem(location, drop));

        // Handling experience drop.
        int experience = event.getDroppedExp();

        // Checking that the amount of experience is strictly positive.
        // Indeed, even if this check isn't made and the amount is 0, an
        // experience orb will spawn anyway.
        if(experience > 0) {

            ExperienceOrb orb = world.spawn(location, ExperienceOrb.class);
            orb.setExperience(event.getDroppedExp());
        }
    }
}
