package com.github.syr0ws.universe.sdk.modules.weather.impl;

import com.github.syr0ws.universe.sdk.modules.weather.Weather;
import com.github.syr0ws.universe.sdk.modules.weather.WeatherModel;
import com.github.syr0ws.universe.sdk.modules.weather.WeatherUtils;
import org.bukkit.World;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CraftWeatherModel implements WeatherModel {

    private final Map<World, Weather> worldWeathers = new HashMap<>();

    @Override
    public void setWeather(World world, Weather weather) {

        if(world == null)
            throw new IllegalArgumentException("World cannot be null.");

        if(weather == null)
            throw new IllegalArgumentException("Weather cannot be null.");

        if(this.hasWeather(world))
            this.removeWeather(world);

        // Storing world weather.
        this.worldWeathers.put(world, weather);

        // Setting in game weather.
        WeatherUtils.setWeather(world, weather);
    }

    @Override
    public void removeWeather(World world) {

        if(world == null)
            throw new IllegalArgumentException("World cannot be null.");

        if(!this.hasWeather(world))
            throw new IllegalArgumentException("World doesn't have weather.");

        world.setClearWeatherDuration(0);
        world.setWeatherDuration(0);
        world.setThunderDuration(0);

        this.worldWeathers.remove(world);
    }

    @Override
    public boolean hasWeather(World world) {
        return this.worldWeathers.containsKey(world);
    }

    @Override
    public Optional<Weather> getWeather(World world) {
        return Optional.ofNullable(this.worldWeathers.get(world));
    }

    @Override
    public Map<World, Weather> getWorldWeathers() {
        return Collections.unmodifiableMap(this.worldWeathers);
    }
}
