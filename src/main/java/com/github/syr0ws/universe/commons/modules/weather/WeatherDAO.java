package com.github.syr0ws.universe.commons.modules.weather;

import org.bukkit.World;

import java.util.Map;

public interface WeatherDAO {

    Map<World, Weather> loadWeathers(String path);
}
