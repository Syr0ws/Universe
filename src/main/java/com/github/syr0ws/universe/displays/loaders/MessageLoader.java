package com.github.syr0ws.universe.displays.loaders;

import com.github.syr0ws.universe.displays.Display;
import com.github.syr0ws.universe.displays.DisplayLoader;
import com.github.syr0ws.universe.displays.impl.Message;
import org.bukkit.configuration.ConfigurationSection;

public class MessageLoader implements DisplayLoader {

    @Override
    public Display load(ConfigurationSection section) {

        String text = section.getString("text", "");

        return new Message(text);
    }
}

