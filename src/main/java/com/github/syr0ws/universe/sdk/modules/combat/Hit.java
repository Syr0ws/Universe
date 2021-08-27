package com.github.syr0ws.universe.sdk.modules.combat;

import org.bukkit.entity.Player;

public class Hit {

    private final Player damager;
    private final double damages;
    private final long time;

    public Hit(Player damager, double damages, long time) {

        if(damager == null)
            throw new IllegalArgumentException("Damager cannot be null.");

        if(damages <= 0)
            throw new IllegalArgumentException("Number of damages cannot be negative.");

        if(time < 0)
            throw new IllegalArgumentException("Time cannot be negative.");

        if(time > System.currentTimeMillis())
            throw new IllegalArgumentException("Time must be lower than current time.");

        this.damager = damager;
        this.damages = damages;
        this.time = time;
    }

    public Player getDamager() {
        return this.damager;
    }

    public double getDamages() {
        return this.damages;
    }

    public long getTime() {
        return this.time;
    }
}
