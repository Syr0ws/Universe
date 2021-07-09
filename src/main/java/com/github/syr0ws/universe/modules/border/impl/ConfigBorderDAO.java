package com.github.syr0ws.universe.modules.border.impl;

import com.github.syr0ws.universe.modules.border.Border;
import com.github.syr0ws.universe.modules.border.BorderDAO;
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
    public Border loadBorder(String name) {

        ConfigurationSection section = this.getBorderSection();

        if(!section.isConfigurationSection(name))
            throw new IllegalArgumentException(String.format("Section '%s.%s' not found.", section.getName(), name));

        return this.loadBorder(section.getConfigurationSection("name"));
    }

    @Override
    public Collection<? extends Border> loadBorders() {

        ConfigurationSection section = this.getBorderSection();

        List<Border> borders = new ArrayList<>();

        for(String key : section.getKeys(false)) {

            if(!section.isConfigurationSection(key)) continue;

            Border border = this.loadBorder(section.getConfigurationSection(key));

            borders.add(border);
        }
        return borders;
    }

    private Border loadBorder(ConfigurationSection section) {

        double centerX = section.getDouble("center.x");
        double centerZ = section.getDouble("center.z");
        double damages = section.getDouble("damages");
        double size = section.getDouble("size");

        String world = section.getString("world");

        Border border = new CraftBorder(world);

        border.setCenter(centerX, centerZ);
        border.setSize(size);
        border.setDamages(damages);

        return border;
    }

    private ConfigurationSection getBorderSection() {

        if(!this.config.isSet(BORDER_SECTION))
            throw new IllegalArgumentException(String.format("ConfigurationSection '%s' not found.", BORDER_SECTION));

        return this.config.getConfigurationSection(BORDER_SECTION);
    }
}
