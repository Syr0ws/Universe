package com.github.syr0ws.universe.sdk.displays.loaders;

import com.github.syr0ws.universe.sdk.displays.Display;
import com.github.syr0ws.universe.sdk.displays.DisplayLoader;
import com.github.syr0ws.universe.sdk.displays.types.LegacyActionBar;
import com.github.syr0ws.universe.sdk.displays.types.NewActionBar;
import com.github.syr0ws.universe.commons.modules.lang.LangService;
import com.github.syr0ws.universe.sdk.tools.Version;
import org.bukkit.configuration.ConfigurationSection;

public class ActionBarLoader implements DisplayLoader {

    private static final Version VERSION = new Version();

    private final LangService service;

    public ActionBarLoader() {
        this.service = null;
    }

    public ActionBarLoader(LangService service) {
        this.service = service;
    }

    @Override
    public Display load(ConfigurationSection section) {

        String text = section.getString("text");

        return VERSION.isLegacy() ? new LegacyActionBar(this.service, text) : new NewActionBar(this.service, text);
    }
}
