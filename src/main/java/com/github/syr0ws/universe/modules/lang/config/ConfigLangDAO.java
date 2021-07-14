package com.github.syr0ws.universe.modules.lang.config;

import com.github.syr0ws.universe.Game;
import com.github.syr0ws.universe.modules.lang.LangDAO;
import com.github.syr0ws.universe.modules.lang.LangException;
import com.github.syr0ws.universe.modules.lang.messages.Message;
import com.github.syr0ws.universe.utils.FileUtils;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public class ConfigLangDAO implements LangDAO {

    private static final String LANG_FOLDER = "langs";

    private final Game game;

    public ConfigLangDAO(Game game) {

        if(game == null)
            throw new IllegalArgumentException("Game cannot be null.");

        this.game = game;
    }

    @Override
    public Map<String, Message> loadMessages(Locale locale) throws LangException {

        Path path = this.getLangFile(locale);

        this.createLangFolder();
        this.createLangFile(locale);

        Map<String, Message> messages = new HashMap<>();

        YamlConfiguration config = YamlConfiguration.loadConfiguration(path.toFile());

        ConfigMessageFactory factory = new ConfigMessageFactory(config);

        for(String key : config.getKeys(false)) {

            Optional<Message> optional = factory.getMessage(key);
            optional.ifPresent(message -> messages.put(key.toLowerCase(), message));
        }

        return messages;
    }

    private void createLangFile(Locale locale) throws LangException {

        Path path = this.getLangFile(locale);

        if(Files.exists(path)) return;

        try {
            FileUtils.copyResource(this.game, path, LANG_FOLDER + "/" + path.getFileName());
        } catch (IOException e) {
            throw new LangException(e.getMessage(), e.getCause());
        }
    }

    private void createLangFolder() throws LangException {

        Path folder = this.getLangFolder();

        if(Files.exists(folder)) return;

        try {
            Files.createDirectory(folder);
        } catch (IOException e) {
            throw new LangException(String.format("Cannot create folder '%s'.", LANG_FOLDER), e.getCause());
        }
    }

    private Path getLangFile(Locale locale) {
        String file = String.format("lang_%s.yml", locale.getLanguage().toUpperCase());
        return Paths.get(this.getLangFolder() + File.separator + file);
    }

    private Path getLangFolder() {
        return Paths.get(this.game.getDataFolder() + File.separator + LANG_FOLDER);
    }
}
