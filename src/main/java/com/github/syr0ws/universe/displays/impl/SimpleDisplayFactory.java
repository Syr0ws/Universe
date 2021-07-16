package com.github.syr0ws.universe.displays.impl;

import com.github.syr0ws.universe.displays.Display;
import com.github.syr0ws.universe.displays.DisplayFactory;
import com.github.syr0ws.universe.displays.DisplayLoader;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SimpleDisplayFactory implements DisplayFactory {

    private final Map<String, DisplayLoader> loaders = new HashMap<>();

    @Override
    public Display getDisplay(ConfigurationSection section) {

        String type = section.getString("type", "").toUpperCase();

        if(!this.loaders.containsKey(type))
            throw new IllegalArgumentException(String.format("No loader found for type '%s'.", type));

        DisplayLoader loader = this.loaders.get(type);

        return loader.load(section);
    }

    @Override
    public void registerLoader(String type, DisplayLoader loader) {
        this.loaders.put(type.toUpperCase(), loader);
    }

    @Override
    public void unregisterLoader(String type) {
        this.loaders.remove(type.toUpperCase());
    }

    @Override
    public Map<String, DisplayLoader> getLoaders() {
        return Collections.unmodifiableMap(this.loaders);
    }
}
