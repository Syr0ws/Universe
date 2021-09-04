package com.github.syr0ws.universe.api;

import com.github.syr0ws.universe.api.game.controller.GameController;
import com.github.syr0ws.universe.api.game.model.GameModel;
import com.github.syr0ws.universe.api.game.view.GameViewHandler;
import com.github.syr0ws.universe.api.modules.ModuleService;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicesManager;

public interface GamePlugin extends Plugin {

    GameModel getGameModel();

    GameController getGameController();

    GameViewHandler getGameViewHandler();

    ModuleService getModuleService();

    ServicesManager getServicesManager();
}
