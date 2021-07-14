package com.github.syr0ws.universe.modules.lang.config;

import com.github.syr0ws.universe.modules.lang.messages.Message;
import com.github.syr0ws.universe.modules.lang.messages.MessageLoader;
import com.github.syr0ws.universe.modules.lang.messages.impl.TextList;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class ConfigTextListLoader implements MessageLoader {

    private final FileConfiguration config;

    public ConfigTextListLoader(FileConfiguration config) {

        if(config == null)
            throw new IllegalArgumentException("FileConfiguration cannot be null.");

        this.config = config;
    }

    @Override
    public Message load(String key) {
        List<String> list = this.config.getStringList(key);
        return new TextList(list);
    }

    @Override
    public boolean canLoad(String key) {
        return this.config.isList(key);
    }
}
