package com.github.syr0ws.universe.sdk.modules.weather.impl;

import com.github.syr0ws.universe.sdk.modules.weather.Weather;
import com.github.syr0ws.universe.sdk.modules.weather.WeatherDAO;
import com.github.syr0ws.universe.sdk.modules.weather.WeatherModel;
import com.github.syr0ws.universe.sdk.modules.weather.WeatherService;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.Map;

public class CraftWeatherService implements WeatherService {

    private final WeatherDAO dao;
    private final WeatherModel model;

    public CraftWeatherService(WeatherDAO dao, WeatherModel model) {

        if(dao == null)
            throw new IllegalArgumentException("WeatherDAO cannot be null.");

        if(model == null)
            throw new IllegalArgumentException("WeatherModel cannot be null.");

        this.dao = dao;
        this.model = model;
    }

    @Override
    public void loadWeathers() {

        Map<World, Weather> worldWeathers = this.dao.loadWeathers("weathers");
        worldWeathers.forEach(this.model::setWeather);
    }

    @Override
    public void reloadWeathers() {

        Bukkit.getWorlds().stream()
                .filter(this.model::hasWeather)
                .forEach(this.model::removeWeather);

        this.loadWeathers();
    }
}
