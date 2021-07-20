package com.github.syr0ws.universe.commons.modules.lang.config;

import com.github.syr0ws.universe.commons.modules.lang.messages.Message;
import com.github.syr0ws.universe.commons.modules.lang.messages.MessageFactory;
import com.github.syr0ws.universe.commons.modules.lang.messages.MessageLoader;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConfigMessageFactory implements MessageFactory {

    private final List<MessageLoader> loaders = new ArrayList<>();

    public ConfigMessageFactory(FileConfiguration config) {

        if(config == null)
            throw new IllegalArgumentException("FileConfiguration cannot be null.");

        // Adding loaders.
        this.loaders.add(new ConfigTextMessageLoader(config));
        this.loaders.add(new ConfigTextListLoader(config));
    }

    @Override
    public Optional<Message> getMessage(String key) {
        return this.loaders.stream()
                .filter(loader -> loader.canLoad(key))
                .findFirst()
                .map(loader -> loader.load(key));
    }
}
