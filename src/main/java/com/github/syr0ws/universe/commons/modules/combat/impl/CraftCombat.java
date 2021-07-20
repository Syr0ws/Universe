package com.github.syr0ws.universe.commons.modules.combat.impl;

import com.github.syr0ws.universe.commons.modules.combat.Combat;
import com.github.syr0ws.universe.commons.modules.combat.Hit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class CraftCombat implements Combat {

    private final Player player;
    private final List<Hit> hits;
    private long lastDamageTime;

    public CraftCombat(Player player) {

        if(player == null)
            throw new IllegalArgumentException("Player cannot be null.");

        this.player = player;
        this.hits = new ArrayList<>();
    }

    public void addHit(Hit hit) {

        if(hit == null)
            throw new IllegalArgumentException("Hit cannot be null.");

        if(hit.getDamager().equals(this.player))
            throw new IllegalArgumentException("A player cannot be in combat with himself.");

        if(this.hits.contains(hit))
            throw new IllegalArgumentException("Hit already contained.");

        this.hits.add(hit);
        this.setLastDamageTime(hit.getTime());
    }

    public void removeHit(Hit hit) {

        if(!this.hits.contains(hit))
            throw new IllegalArgumentException("Hit not contained.");

        this.hits.remove(hit);
    }

    public void removeHits(Predicate<Hit> predicate) {
        this.hits.removeIf(predicate);
    }

    public void setLastDamageTime(long time) {

        if(time <= 0)
            throw new IllegalArgumentException("Time cannot be negative.");

        if(time > System.currentTimeMillis())
            throw new IllegalArgumentException("Time must be lower than current time.");

        this.lastDamageTime = time;
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public List<Hit> getHits() {
        return Collections.unmodifiableList(this.hits);
    }

    @Override
    public long getLastDamageTime() {
        return this.lastDamageTime;
    }
}
