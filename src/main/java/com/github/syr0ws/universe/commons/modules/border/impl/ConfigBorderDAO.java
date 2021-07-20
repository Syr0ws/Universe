package com.github.syr0ws.universe.commons.modules.border.impl;

import com.github.syr0ws.universe.commons.modules.border.Border;
import com.github.syr0ws.universe.commons.modules.border.BorderDAO;
import com.github.syr0ws.universe.commons.modules.border.BorderException;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ConfigBorderDAO implements BorderDAO {

    private static final String BORDER_SECTION = "borders";

    private final FileConfiguration config;

    public ConfigBorderDAO(FileConfiguration config) {

        if(config == null)
            throw new IllegalArgumentException("FileConfiguration cannot be null.");

        this.config = config;
    }

    @Override
    public Border loadBorder(String name) throws BorderException {

        ConfigurationSection section = this.getBorderSection();

        // Checking if a section with the specified name exists.
        if(!section.isConfigurationSection(name))
            throw new IllegalArgumentException(String.format("Section '%s.%s' not found.", section.getCurrentPath(), name));

        // Returning the loaded border.
        return this.loadBorder(section.getConfigurationSection("name"));
    }

    @Override
    public Collection<? extends Border> loadBorders() throws BorderException {

        ConfigurationSection section = this.getBorderSection();

        List<Border> borders = new ArrayList<>();

        for(String key : section.getKeys(false)) {

            // Checking if the key corresponds to a section.
            if(!section.isConfigurationSection(key)) continue;

            // Loading the border.
            Border border = this.loadBorder(section.getConfigurationSection(key));

            borders.add(border);
        }
        return borders;
    }

    private Border loadBorder(ConfigurationSection section) throws BorderException {

        if(!section.isSet("world"))
            throw new BorderException(String.format("Key 'world' not found in '%s'.", section.getCurrentPath()));

        String world = section.getString("world");

        double centerX = section.getDouble("center.x", 0);
        double centerZ = section.getDouble("center.z", 0);
        double damages = section.getDouble("damages", 0.2);
        double size = section.getDouble("size", Double.MAX_VALUE);
        double safeZoneDistance = section.getDouble("safe-zone-distance", 5);

        int warningTime = section.getInt("warning-time", 15);
        int warningDistance = section.getInt("warning-distance", 5);

        Border border;

        try {

            border = new CraftBorder(world);

            border.setCenter(centerX, centerZ);
            border.setSize(size);
            border.setDamages(damages);
            border.setSafeZoneDistance(safeZoneDistance);
            border.setWarningTime(warningTime);
            border.setWarningDistance(warningDistance);

        } catch (IllegalArgumentException e) {
            throw new BorderException(String.format("Cannot load border at '%s'.", section.getCurrentPath()), e);
        }

        return border;
    }

    private ConfigurationSection getBorderSection() {

        if(!this.config.isSet(BORDER_SECTION))
            throw new IllegalArgumentException(String.format("ConfigurationSection '%s' not found.", BORDER_SECTION));

        return this.config.getConfigurationSection(BORDER_SECTION);
    }
}
