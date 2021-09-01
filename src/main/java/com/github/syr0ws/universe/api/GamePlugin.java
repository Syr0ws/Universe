package com.github.syr0ws.universe.api;

import com.github.syr0ws.universe.api.game.controller.GameController;
import com.github.syr0ws.universe.api.game.model.GameModel;
import com.github.syr0ws.universe.api.game.view.GameViewHandler;
import com.github.syr0ws.universe.api.modules.ModuleService;

public interface GamePlugin {

    ModuleService getModuleService();

    GameModel getGameModel();

    GameController getGameController();

    GameViewHandler getGameViewHandler();
}
