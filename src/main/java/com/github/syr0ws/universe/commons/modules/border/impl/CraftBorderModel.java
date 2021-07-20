package com.github.syr0ws.universe.commons.modules.border.impl;

import com.github.syr0ws.universe.commons.modules.border.Border;
import com.github.syr0ws.universe.commons.modules.border.BorderModel;
import org.bukkit.World;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CraftBorderModel implements BorderModel {

    private final Map<String, Border> borders = new HashMap<>();

    @Override
    public void addBorder(Border border) {

        if(border == null)
            throw new IllegalArgumentException("Border cannot be null.");

        this.borders.put(border.getWorld(), border);
    }

    @Override
    public void removeBorder(Border border) {

        if(border == null)
            throw new IllegalArgumentException("Border cannot be null.");

        this.borders.remove(border.getWorld());
    }

    @Override
    public boolean hasBorder(World world) {
        return this.borders.containsKey(world.getName());
    }

    @Override
    public Optional<Border> getBorder(World world) {
        return Optional.ofNullable(this.borders.get(world.getName()));
    }
}
