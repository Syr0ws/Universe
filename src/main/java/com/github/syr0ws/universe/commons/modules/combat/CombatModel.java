package com.github.syr0ws.universe.commons.modules.combat;

import com.github.syr0ws.universe.commons.modules.combat.settings.CombatSettings;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Optional;

public interface CombatModel {

    CombatSettings getSettings();

    boolean isInCombat(Player player);

    Optional<? extends Combat> getCombat(Player player);

    Collection<? extends Combat> getCombats();

    Collection<? extends Player> getPlayersInCombat();
}
