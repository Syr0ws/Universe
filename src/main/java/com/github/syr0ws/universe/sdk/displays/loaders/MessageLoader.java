package com.github.syr0ws.universe.sdk.displays.loaders;

import com.github.syr0ws.universe.api.displays.Display;
import com.github.syr0ws.universe.api.displays.DisplayLoader;
import com.github.syr0ws.universe.sdk.displays.types.Message;
import com.github.syr0ws.universe.sdk.modules.lang.LangService;
import org.bukkit.configuration.ConfigurationSection;

public class MessageLoader implements DisplayLoader {

    private final LangService service;

    public MessageLoader() {
        this.service = null;
    }

    public MessageLoader(LangService service) {
        this.service = service;
    }

    @Override
    public Display load(ConfigurationSection section) {

        String text = section.getString("text", "");

        return new Message(this.service, text);
    }
}

