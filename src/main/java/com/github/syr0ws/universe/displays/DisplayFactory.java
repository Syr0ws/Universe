package com.github.syr0ws.universe.displays;

import com.github.syr0ws.universe.displays.Display;
import com.github.syr0ws.universe.displays.DisplayLoader;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Map;

public interface DisplayFactory {

    Display getDisplay(ConfigurationSection section);

    void registerLoader(String type, DisplayLoader loader);

    void unregisterLoader(String type);

    Map<String, DisplayLoader> getLoaders();
}
