package com.github.syr0ws.universe.displays.loaders;

import com.github.syr0ws.universe.displays.Display;
import com.github.syr0ws.universe.displays.DisplayLoader;
import com.github.syr0ws.universe.displays.types.LegacyTitle;
import com.github.syr0ws.universe.displays.types.NewTitle;
import com.github.syr0ws.universe.displays.types.Title;
import com.github.syr0ws.universe.modules.lang.LangService;
import com.github.syr0ws.universe.tools.Version;
import org.bukkit.configuration.ConfigurationSection;

public class TitleLoader implements DisplayLoader {

    private static final Version VERSION = new Version();

    private final LangService service;

    public TitleLoader() {
        this.service = null;
    }

    public TitleLoader(LangService service) {
        this.service = service;
    }

    @Override
    public Display load(ConfigurationSection section) {

        String title = section.getString("title", "");
        String subtitle = section.getString("subtitle", "");

        int fadeIn = section.getInt("fade-in", Title.DEFAULT_FADE_IN);
        int stay = section.getInt("stay", Title.DEFAULT_STAY);
        int fadeOut = section.getInt("fade-out", Title.DEFAULT_FADE_OUT);

        return VERSION.isLegacy() ?
                new LegacyTitle(this.service, title, subtitle, fadeIn, stay, fadeOut) :
                new NewTitle(this.service, title, subtitle, fadeIn, stay, fadeOut);
    }
}
