package com.github.syr0ws.universe.commons.modules.weather;

import org.bukkit.World;

public class WeatherUtils {

    public static void setWeather(World world, Weather weather) {

        world.setWeatherDuration(12000);
        world.setThunderDuration(12000);

        if (weather == Weather.SUNNY) {
            world.setStorm(false);
            world.setThundering(false);
        }
    }
}
