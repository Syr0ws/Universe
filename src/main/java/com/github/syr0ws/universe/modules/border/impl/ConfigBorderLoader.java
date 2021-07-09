package com.github.syr0ws.universe.modules.border.impl;

import com.github.syr0ws.universe.modules.border.BorderLoader;
import org.bukkit.Bukkit;
import org.bukkit.WorldBorder;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigBorderLoader implements BorderLoader {

    private static final String BORDER_SECTION = "borders";

    private final FileConfiguration config;

    public ConfigBorderLoader(FileConfiguration config) {

        if(config == null)
            throw new IllegalArgumentException("FileConfiguration cannot be null.");

        this.config = config;
    }

    @Override
    public void loadBorder(String name) {

        ConfigurationSection section = this.getBorderSection();

        if(!section.isConfigurationSection(name))
            throw new IllegalArgumentException(String.format("Section '%s.%s' not found.", section.getName(), name));

        this.loadBorder(section.getConfigurationSection("name"));
    }

    @Override
    public void loadBorders() {

        ConfigurationSection section = this.getBorderSection();

        for(String key : section.getKeys(false)) {

            if(!section.isConfigurationSection(key)) continue;

            this.loadBorder(section.getConfigurationSection(key));
        }
    }

    private void loadBorder(ConfigurationSection section) {

        double centerX = section.getDouble("center.x");
        double centerZ = section.getDouble("center.z");
        double damages = section.getDouble("damages");
        double size = section.getDouble("size");

        String world = section.getString("world");

        WorldBorder border = Bukkit.getWorld(world).getWorldBorder();
        border.setCenter(centerX, centerZ);
        border.setSize(size);
        border.setDamageAmount(damages);
    }

    private ConfigurationSection getBorderSection() {

        if(!this.config.isSet(BORDER_SECTION))
            throw new IllegalArgumentException(String.format("ConfigurationSection '%s' not found.", BORDER_SECTION));

        return this.config.getConfigurationSection(BORDER_SECTION);
    }
}
