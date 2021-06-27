package com.github.syr0ws.universe.modules.combat;

import org.bukkit.entity.Player;

import java.util.Collection;

public interface Combat {

    long getLastDamageTime();

    Player getPlayer();

    Collection<Hit> getHits();
}
