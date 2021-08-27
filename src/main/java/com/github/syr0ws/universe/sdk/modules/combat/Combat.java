package com.github.syr0ws.universe.sdk.modules.combat;

import org.bukkit.entity.Player;

import java.util.Collection;

public interface Combat {

    long getLastDamageTime();

    Player getPlayer();

    Collection<Hit> getHits();
}
