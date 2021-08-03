package com.github.syr0ws.universe.commons.modules.weather;

import org.bukkit.event.Listener;

public class WeatherListener implements Listener {

    private final WeatherModel model;

    public WeatherListener(WeatherModel model) {

        if(model == null)
            throw new IllegalArgumentException("WeatherModel cannot be null.");

        this.model = model;
    }

    /*
    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {

        System.out.println(event.getWorld().getName());

        World world = event.getWorld();

        if(!this.model.hasWeather(world)) return;

        Weather weather = this.model.getWeather(world);

        if(weather != Weather.NORMAL) event.setCancelled(true);
    }
     */
}
