package com.github.syr0ws.universe.commons.modules.border;

import org.bukkit.World;

import java.util.Optional;

public interface BorderModel {

    void addBorder(Border border);

    void removeBorder(Border border);

    boolean hasBorder(World world);

    Optional<Border> getBorder(World world);
}
