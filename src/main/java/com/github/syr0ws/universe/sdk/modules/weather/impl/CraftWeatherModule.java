package com.github.syr0ws.universe.sdk.modules.weather.impl;

import com.github.syr0ws.universe.sdk.modules.GameModule;
import com.github.syr0ws.universe.sdk.modules.ModuleEnum;
import com.github.syr0ws.universe.sdk.modules.weather.*;
import com.github.syr0ws.universe.sdk.Game;
import com.github.syr0ws.universe.sdk.listeners.ListenerManager;

public class CraftWeatherModule extends GameModule implements WeatherModule {

    private WeatherModel model;
    private WeatherService service;

    public static final String MODULE_NAME = "WeatherModule";

    public CraftWeatherModule(Game game) {
        super(game);
    }

    @Override
    public void load() {
        super.load();
        super.loadConfig();
    }

    @Override
    public void enable() {
        super.enable();

        WeatherDAO dao = new ConfigWeatherDAO(this.getConfig());

        this.model = new CraftWeatherModel();

        this.service = new CraftWeatherService(dao, this.model);
        this.service.loadWeathers();

        this.registerListeners();
    }

    @Override
    public String getName() {
        return MODULE_NAME;
    }

    @Override
    public String getConfigName() {
        return "weather-module.yml";
    }

    @Override
    public boolean useDefaultConfig() {
        return true;
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
