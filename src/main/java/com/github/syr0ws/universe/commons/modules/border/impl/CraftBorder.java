package com.github.syr0ws.universe.commons.modules.border.impl;

import com.github.syr0ws.universe.commons.modules.border.Border;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;

public class CraftBorder implements Border {

    private final String world;

    public CraftBorder(String world) {

        if(world == null)
            throw new IllegalArgumentException("World cannot be null.");

        this.world = world;
    }

    @Override
    public void setDamages(double damages) {

        if(damages < 0)
            throw new IllegalArgumentException("Damages must be positive.");

        this.getWorldBorder().setDamageAmount(damages);
    }

    @Override
    public void setSize(double size) {

        if(size <= 0)
            throw new IllegalArgumentException("Size must be positive.");

        this.getWorldBorder().setSize(size);
    }

    @Override
    public void setSize(int size, int seconds) {
        // TODO to implement.
    }

    @Override
    public void setCenter(double x, double z) {
        this.getWorldBorder().setCenter(x, z);
    }

    @Override
    public void setCenter(double x, double z, int seconds) {
        // TODO to implement.
    }

    @Override
    public void setSafeZoneDistance(double distance) {

        if(distance < 0)
            throw new IllegalArgumentException("Distance must be positive.");

        this.getWorldBorder().setDamageBuffer(distance);
    }

    @Override
    public void setWarningDistance(int distance) {

        if(distance < 0)
            throw new IllegalArgumentException("Distance must be positive.");

        this.getWorldBorder().setWarningDistance(distance);
    }

    @Override
    public void setWarningTime(int seconds) {

        if(seconds < 0)
            throw new IllegalArgumentException("Number of seconds must be positive.");

        this.getWorldBorder().setWarningTime(seconds);
    }

    @Override
    public String getWorld() {
        return this.world;
    }

    @Override
    public double getCenterX() {
        return this.getWorldBorder().getCenter().getX();
    }

    @Override
    public double getCenterZ() {
        return this.getWorldBorder().getCenter().getZ();
    }

    @Override
    public double getSize() {
        return this.getWorldBorder().getSize();
    }

    @Override
    public double getDamages() {
        return this.getWorldBorder().getDamageAmount();
    }

    @Override
    public double getSafeZoneDistance() {
        return this.getWorldBorder().getDamageBuffer();
    }

    @Override
    public int getWarningDistance() {
        return this.getWorldBorder().getWarningDistance();
    }

    @Override
    public int getWarningTime() {
        return this.getWorldBorder().getWarningTime();
    }

    private WorldBorder getWorldBorder() {

        World world = Bukkit.getWorld(this.world);

        if(world == null)
            throw new NullPointerException(String.format("World '%s' not found.", this.world));

        return world.getWorldBorder();
    }
}
