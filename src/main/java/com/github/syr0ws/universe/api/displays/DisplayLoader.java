package com.github.syr0ws.universe.api.displays;

import org.bukkit.configuration.ConfigurationSection;

public interface DisplayLoader {

    Display load(ConfigurationSection section);
}
