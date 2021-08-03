package com.github.syr0ws.universe.commons.modules.weather.impl;

import com.github.syr0ws.universe.commons.modules.weather.Weather;
import com.github.syr0ws.universe.commons.modules.weather.WeatherModel;
import org.bukkit.World;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

        world.setWeatherDuration(weather == Weather.NORMAL ? 0 : Integer.MAX_VALUE);
        world.setThunderDuration(weather == Weather.NORMAL ? 0 : Integer.MAX_VALUE);

        switch (weather) {
            case SUNNY:
                world.setStorm(false);
                world.setThundering(false);
                break;
            case RAIN:
                world.setStorm(true);
                world.setThundering(false);
                break;
            case THUNDER:
                world.setStorm(true);
                world.setThundering(true);
                break;
        }

        this.worldWeathers.put(world, weather);
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
    public Weather getWeather(World world) {

        if(!this.hasWeather(world))
            throw new IllegalArgumentException("World doesn't have weather.");

        return this.worldWeathers.get(world);
    }

    @Override
    public Map<World, Weather> getWorldWeathers() {
        return Collections.unmodifiableMap(this.worldWeathers);
    }
}
