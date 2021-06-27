package com.github.syr0ws.universe.modules.combat.impl;

import com.github.syr0ws.universe.modules.combat.CombatException;
import com.github.syr0ws.universe.modules.combat.CombatService;
import com.github.syr0ws.universe.modules.combat.Hit;
import com.github.syr0ws.universe.modules.combat.events.CombatStartEvent;
import com.github.syr0ws.universe.modules.combat.events.CombatStopEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DefaultCombatService implements CombatService {

    private final Map<Player, PlayerCombat> combats = new HashMap<>();

    private PlayerCombat setInCombat(Player player) {

        PlayerCombat combat = new PlayerCombat(player);

        this.combats.put(player, combat);

        CombatStartEvent event = new CombatStartEvent(combat);
        Bukkit.getPluginManager().callEvent(event);

        return combat;
    }

    @Override
    public void onHit(Player player, Hit hit) {

        if(!this.isInCombat(player)) this.setInCombat(player);

        if(!this.isInCombat(hit.getDamager())) this.setInCombat(hit.getDamager());

        // Victim combat.
        PlayerCombat victimCombat = this.combats.get(player);
        victimCombat.addHit(hit); // Adding hit.
        victimCombat.setLastDamageTime(System.currentTimeMillis()); // Updating last damage.

        // Damager combat.
        PlayerCombat damagerCombat = this.combats.get(hit.getDamager());
        // Not hit to add because hits are only for the player who receives them.
        damagerCombat.setLastDamageTime(System.currentTimeMillis()); // Updating last damage.
    }

    @Override
    public PlayerCombat stopCombat(Player player) {

        if(!this.isInCombat(player))
            throw new CombatException("Player not in combat.");

        PlayerCombat combat = this.combats.remove(player);

        CombatStopEvent event = new CombatStopEvent(combat);
        Bukkit.getPluginManager().callEvent(event);

        return combat;
    }

    @Override
    public void stopCombats() {
        this.combats.keySet().forEach(this::stopCombat);
    }

    @Override
    public boolean isInCombat(Player player) {
        return this.combats.containsKey(player);
    }

    @Override
    public Optional<PlayerCombat> getCombat(Player player) {
        return Optional.ofNullable(this.combats.get(player));
    }

    @Override
    public Collection<PlayerCombat> getCombats() {
        return this.combats.values();
    }
}
