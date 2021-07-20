package com.github.syr0ws.universe.commons.modules.lang.config;

import com.github.syr0ws.universe.commons.modules.lang.messages.Message;
import com.github.syr0ws.universe.commons.modules.lang.messages.MessageLoader;
import com.github.syr0ws.universe.commons.modules.lang.messages.impl.Text;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigTextMessageLoader implements MessageLoader {

    private final FileConfiguration config;

    public ConfigTextMessageLoader(FileConfiguration config) {

        if(config == null)
            throw new IllegalArgumentException("FileConfiguration cannot be null.");

        this.config = config;
    }

    @Override
    public Message load(String key) {
        String text = this.config.getString(key);
        return new Text(text);
    }

    @Override
    public boolean canLoad(String key) {
        return this.config.isString(key);
    }
}
