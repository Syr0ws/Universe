package com.github.syr0ws.universe.commons.modules.combat;

import org.bukkit.entity.Player;

public interface CombatService {

    void die(Player player);

    void onHit(Player player, Hit hit);

    Combat stopCombat(Player player);

    void stopCombats();
}
