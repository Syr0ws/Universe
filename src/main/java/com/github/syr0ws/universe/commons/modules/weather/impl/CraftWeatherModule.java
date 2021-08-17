package com.github.syr0ws.universe.commons.modules.weather.impl;

import com.github.syr0ws.universe.commons.modules.GameModule;
import com.github.syr0ws.universe.commons.modules.ModuleEnum;
import com.github.syr0ws.universe.commons.modules.ModuleException;
import com.github.syr0ws.universe.commons.modules.weather.*;
import com.github.syr0ws.universe.sdk.Game;
import com.github.syr0ws.universe.sdk.listeners.ListenerManager;

public class CraftWeatherModule extends GameModule implements WeatherModule {

    private WeatherModel model;
    private WeatherService service;

    public CraftWeatherModule(Game game) {
        super(game);
    }

    @Override
    public void enable() throws ModuleException {

        WeatherDAO dao = new ConfigWeatherDAO(this.getConfig());

        this.model = new CraftWeatherModel();

        this.service = new CraftWeatherService(dao, this.model);
        this.service.loadWeathers();

        this.registerListeners();
    }

    @Override
    public void disable() throws ModuleException {

    }

    @Override
    public String getName() {
        return ModuleEnum.WEATHER_MODULE.getName();
    }

    @Override
    public WeatherModel getWeatherModel() {
        return this.model;
    }

    @Override
    public WeatherService getWeatherService() {
        return this.service;
    }

    private void registerListeners() {

        ListenerManager manager = super.getListenerManager();
        manager.addListener(new WeatherListener(this.model));
    }
}
