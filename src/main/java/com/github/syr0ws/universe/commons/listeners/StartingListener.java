package com.github.syr0ws.universe.commons.listeners;

import com.github.syr0ws.universe.commons.settings.GameSettings;
import com.github.syr0ws.universe.sdk.events.GamePlayerQuitEvent;
import com.github.syr0ws.universe.sdk.game.controller.GameController;
import com.github.syr0ws.universe.sdk.game.model.GameException;
import com.github.syr0ws.universe.sdk.game.model.GameModel;
import com.github.syr0ws.universe.sdk.settings.types.MutableSetting;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class StartingListener implements Listener {

    private final GameModel model;
    private final GameController controller;

    public StartingListener(GameModel model, GameController controller) {

        if(model == null)
            throw new IllegalArgumentException("GameModel cannot be null.");

        if(controller == null)
            throw new IllegalArgumentException("GameController cannot be null.");

        this.model = model;
        this.controller = controller;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onGamePlayerJoin(GamePlayerQuitEvent event) {

        GameSettings settings = this.model.getSettings();
        MutableSetting<Integer> minPlayerSetting = settings.getMinPlayerSetting();

        int players = this.model.getOnlinePlayers().size();

        // Checking if there is enough players to continue starting the game.
        if(players >= minPlayerSetting.getValue()) return;

        // Stopping the game.
        try { this.controller.stopGame();
        } catch (GameException e) { e.printStackTrace(); }
    }
}
