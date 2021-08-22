package com.github.syr0ws.universe.sdk;

import com.github.syr0ws.universe.sdk.config.ConfigResourceHandler;
import com.github.syr0ws.universe.sdk.config.YamlConfigResourceHandler;
import com.github.syr0ws.universe.sdk.game.controller.GameController;
import com.github.syr0ws.universe.sdk.game.model.GameModel;
import com.github.syr0ws.universe.sdk.listeners.ListenerManager;
import com.github.syr0ws.universe.commons.modules.GameModuleService;
import com.github.syr0ws.universe.commons.modules.ModuleService;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class Game extends JavaPlugin {

    private static final String CONFIG_KEY = "main";
    private static final String CONFIG_FILE_NAME = "config.yml";

    private final ModuleService service;
    private final ListenerManager listenerManager;

    private final ConfigResourceHandler<YamlConfiguration> handler;
    private YamlConfiguration config;

    public Game() {
        this.service = new GameModuleService();
        this.listenerManager = new ListenerManager(this);

        this.handler = new YamlConfigResourceHandler(this);
        this.handler.addSubConfig(CONFIG_KEY, CONFIG_FILE_NAME);
    }

    @Override
    public void onEnable() {
        this.service.enableModules();
    }

    @Override
    public void onDisable() {
        this.service.disableModules();
    }

    @Override
    public void saveConfig() {

        Path target = Paths.get(this.getDataFolder() + "/" + CONFIG_FILE_NAME);

        // If the config file doesn't exist, creating it.
        if(!Files.exists(target)) {

            try { this.handler.save(target);
            } catch (IOException e) { e.printStackTrace(); }
        }

        try {

            this.config = new YamlConfiguration();
            this.config.load(target.toFile());

        } catch (IOException | InvalidConfigurationException e) { e.printStackTrace(); }
    }

    @Override
    public void saveDefaultConfig() {
        this.saveConfig();
    }

    @Override
    public YamlConfiguration getConfig() {

        if(this.config == null)
            throw new NullPointerException("YamlConfiguration is null.");

        return this.config;
    }

    public abstract GameModel getGameModel();

    public abstract GameController getGameController();

    public ListenerManager getListenerManager() {
        return this.listenerManager;
    }

    public ModuleService getModuleService() {
        return this.service;
    }

    public ConfigResourceHandler<YamlConfiguration> getConfigHandler() {
        return this.handler;
    }
}
