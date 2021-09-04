package com.github.syr0ws.universe.sdk.modules.weather.impl;

import com.github.syr0ws.universe.api.GamePlugin;
import com.github.syr0ws.universe.sdk.Game;
import com.github.syr0ws.universe.sdk.listeners.ListenerManager;
import com.github.syr0ws.universe.sdk.modules.GameModule;
import com.github.syr0ws.universe.sdk.modules.ModuleEnum;
import com.github.syr0ws.universe.sdk.modules.weather.*;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;

public class CraftWeatherModule extends GameModule implements WeatherModule {

    public CraftWeatherModule(Game game) {
        super(game);
    }

    @Override
    public void load() {
        super.loadConfig();
    }

    @Override
    public void enable() {

        // Binding classes.
        this.bindWeatherModel();
        this.bindWeatherService();

        // Registering listeners.
        this.registerListeners();

        // Loading weathers.
        WeatherService service = this.getWeatherService();
        service.loadWeathers();
    }

    @Override
    public void disable() {

    }

    @Override
    public String getName() {
        return ModuleEnum.WEATHER_MODULE.getName();
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
        ServicesManager manager = super.getGame().getServicesManager();
        return manager.load(WeatherModel.class);
    }

    @Override
    public WeatherService getWeatherService() {
        ServicesManager manager = super.getGame().getServicesManager();
        return manager.load(WeatherService.class);
    }

    private void bindWeatherModel() {

        GamePlugin plugin = super.getGame();
        WeatherModel model = new CraftWeatherModel();

        ServicesManager manager = plugin.getServicesManager();
        manager.register(WeatherModel.class, model, plugin, ServicePriority.Normal);
    }

    private void bindWeatherService() {

        GamePlugin plugin = super.getGame();

        WeatherDAO dao = new ConfigWeatherDAO(super.getConfig());
        WeatherModel model = this.getWeatherModel();
        WeatherService service = new CraftWeatherService(dao, model);

        ServicesManager manager = plugin.getServicesManager();
        manager.register(WeatherService.class, service, plugin, ServicePriority.Normal);
    }

    private void registerListeners() {

        WeatherModel model = this.getWeatherModel();

        ListenerManager manager = super.getListenerManager();
        manager.addListener(new WeatherListener(model));
    }
}
