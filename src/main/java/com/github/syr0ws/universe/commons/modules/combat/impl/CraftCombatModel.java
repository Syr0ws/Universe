package com.github.syr0ws.universe.commons.modules.combat.impl;

import com.github.syr0ws.universe.commons.modules.combat.CombatModel;
import com.github.syr0ws.universe.commons.modules.combat.settings.CombatSettings;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CraftCombatModel implements CombatModel {

    private final CombatSettings settings;
    private final Map<Player, CraftCombat> combats = new HashMap<>();

    public CraftCombatModel(CombatSettings settings) {

        if(settings == null)
            throw new IllegalArgumentException("CombatSettings cannot be null.");

        this.settings = settings;
    }

    public CraftCombat setInCombat(Player player) {

        if(this.combats.containsKey(player))
            throw new IllegalArgumentException("Player already in combat.");

        CraftCombat combat = new CraftCombat(player);

        this.combats.put(player, combat);

        return combat;
    }

    public CraftCombat removeCombat(Player player) {

        if(!this.combats.containsKey(player))
            throw new IllegalArgumentException("Player not in combat.");

        return this.combats.remove(player);
    }

    @Override
    public CombatSettings getSettings() {
        return this.settings;
    }

    @Override
    public boolean isInCombat(Player player) {
        return this.combats.containsKey(player);
    }

    @Override
    public Optional<CraftCombat> getCombat(Player player) {
        return Optional.ofNullable(this.combats.get(player));
    }

    @Override
    public Collection<CraftCombat> getCombats() {
        return this.combats.values();
    }

    @Override
    public Collection<? extends Player> getPlayersInCombat() {
        return this.combats.keySet();
    }
}
