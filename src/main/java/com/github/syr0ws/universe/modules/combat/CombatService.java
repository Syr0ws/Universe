package com.github.syr0ws.universe.modules.combat;

import com.github.syr0ws.universe.modules.combat.impl.PlayerCombat;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Optional;

public interface CombatService {

    void onHit(Player player, Hit hit);

    PlayerCombat stopCombat(Player player);

    void stopCombats();

    boolean isInCombat(Player player);

    Optional<? extends Combat> getCombat(Player player);

    Collection<? extends Combat> getCombats();
}
