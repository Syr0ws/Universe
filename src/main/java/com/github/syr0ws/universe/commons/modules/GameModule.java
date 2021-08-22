package com.github.syr0ws.universe.commons.modules;

import com.github.syr0ws.universe.sdk.Game;
import com.github.syr0ws.universe.sdk.listeners.ListenerManager;
import com.github.syr0ws.universe.sdk.utils.FileUtils;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class GameModule implements Module {

    private final Game game;
    private final ListenerManager listenerManager;

    private YamlConfiguration config;

    public GameModule(Game game) {

        if(game == null)
            throw new IllegalArgumentException("Game cannot be null.");

        this.game = game;
        this.listenerManager = new ListenerManager(game);
    }

    public void loadConfig() {

        String configName = this.getConfigName();

        if(!this.useDefaultConfig()) {

            Path target = Paths.get(this.game.getDataFolder() + "/" + configName);

            try {

                FileUtils.copyResource(this.game, target, configName);

                this.config = new YamlConfiguration();
                this.config.load(target.toFile());

            } catch (IOException | InvalidConfigurationException e) { e.printStackTrace(); }

        } else this.game.getConfigHandler().addSubConfig(this.getName(), configName);
    }

    public boolean useDefaultConfig() {
        return false;
    }

    public String getConfigName() {
        return this.getName().toLowerCase() + ".yml";
    }

    public Game getGame() {
        return this.game;
    }

    public FileConfiguration getConfig() {
        return this.useDefaultConfig() ? this.game.getConfig() : this.config;
    }

    public ListenerManager getListenerManager() {
        return this.listenerManager;
    }
}
