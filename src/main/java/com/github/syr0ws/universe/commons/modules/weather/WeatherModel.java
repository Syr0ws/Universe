package com.github.syr0ws.universe.commons.modules.weather;

import org.bukkit.World;

import java.util.Map;
import java.util.Optional;

public interface WeatherModel {

    void setWeather(World world, Weather weather);

    void removeWeather(World world);

    boolean hasWeather(World world);

    Optional<Weather> getWeather(World world);

    Map<World, Weather> getWorldWeathers();
}
