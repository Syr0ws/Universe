package com.github.syr0ws.universe.commons.modules.weather;

import org.bukkit.World;

import java.util.Map;

public interface WeatherModel {

    void setWeather(World world, Weather weather);

    void removeWeather(World world);

    boolean hasWeather(World world);

    Weather getWeather(World world);

    Map<World, Weather> getWorldWeathers();
}
