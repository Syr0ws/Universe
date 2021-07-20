package com.github.syr0ws.universe.sdk.displays;

import org.bukkit.configuration.ConfigurationSection;

public interface DisplayLoader {

    Display load(ConfigurationSection section);
}
