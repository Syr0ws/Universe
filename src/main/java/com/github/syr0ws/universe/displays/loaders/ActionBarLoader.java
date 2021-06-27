package com.github.syr0ws.universe.displays.loaders;

import com.github.syr0ws.universe.displays.Display;
import com.github.syr0ws.universe.displays.DisplayLoader;
import com.github.syr0ws.universe.displays.impl.LegacyActionBar;
import com.github.syr0ws.universe.displays.impl.NewActionBar;
import com.github.syr0ws.universe.tools.Version;
import org.bukkit.configuration.ConfigurationSection;

public class ActionBarLoader implements DisplayLoader {

    private static final Version VERSION = new Version();

    @Override
    public Display load(ConfigurationSection section) {

        String text = section.getString("text");

        return VERSION.isLegacy() ? new LegacyActionBar(text) : new NewActionBar(text);
    }
}
