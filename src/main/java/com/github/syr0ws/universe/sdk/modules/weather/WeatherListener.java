package com.github.syr0ws.universe.sdk.modules.weather;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherListener implements Listener {

    private final WeatherModel model;

    public WeatherListener(WeatherModel model) {

        if(model == null)
            throw new IllegalArgumentException("WeatherModel cannot be null.");

        this.model = model;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onWeatherChange(WeatherChangeEvent event) {

        World world = event.getWorld();

        Weather weather = this.model.getWeather(world).orElse(Weather.NORMAL);

        // toWeatherState() is true when weather will be rainy.
        if(weather == Weather.SUNNY && event.toWeatherState()) event.setCancelled(true);
    }
}
