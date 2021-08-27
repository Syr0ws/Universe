package com.github.syr0ws.universe.sdk.modules.weather.impl;

import com.github.syr0ws.universe.sdk.modules.weather.Weather;
import com.github.syr0ws.universe.sdk.modules.weather.WeatherDAO;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;

public class ConfigWeatherDAO implements WeatherDAO {

    private final FileConfiguration config;

    public ConfigWeatherDAO(FileConfiguration config) {

        if(config == null)
            throw new IllegalArgumentException("FileConfiguration cannot be null.");

        this.config = config;
    }

    @Override
    public Map<World, Weather> loadWeathers(String path) {

        ConfigurationSection section = this.config.getConfigurationSection(path);

        if(section == null)
            throw new IllegalArgumentException(String.format("Invalid path '%s'.", path));

        Map<World, Weather> worldWeathers = new HashMap<>();

        for(String key : section.getKeys(false)) {

            ConfigurationSection worldSection = section.getConfigurationSection(key);

            if(worldSection == null) continue;

            String worldName = worldSection.getString("world", "");
            String weatherName = worldSection.getString("weather", "");

            try {

                World world = this.getWorld(worldName);
                Weather weather = this.getWeather(weatherName);

                worldWeathers.put(world, weather);

            } catch (IllegalArgumentException e) { e.printStackTrace(); }
        }

        return worldWeathers;
    }

    private World getWorld(String name) {

        World world = Bukkit.getWorld(name);

        if(world == null)
            throw new IllegalArgumentException(String.format("Invalid world name '%s'.", name));

        return world;
    }

    private Weather getWeather(String name) {

        try {
            return Weather.valueOf(name);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("Invalid weather '%s'.", name));
        }
    }
}
