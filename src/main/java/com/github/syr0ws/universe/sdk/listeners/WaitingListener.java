package com.github.syr0ws.universe.sdk.listeners;

import com.github.syr0ws.universe.api.game.settings.GameSettings;
import com.github.syr0ws.universe.api.settings.MutableSetting;
import com.github.syr0ws.universe.sdk.events.GamePlayerJoinEvent;
import com.github.syr0ws.universe.api.game.controller.GameController;
import com.github.syr0ws.universe.api.game.model.GameException;
import com.github.syr0ws.universe.api.game.model.GameModel;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class WaitingListener implements Listener {

    private final GameModel model;
    private final GameController controller;

    public WaitingListener(GameModel model, GameController controller) {

        if(model == null)
            throw new IllegalArgumentException("GameModel cannot be null.");

        if(controller == null)
            throw new IllegalArgumentException("GameController cannot be null.");

        this.model = model;
        this.controller = controller;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onGamePlayerJoin(GamePlayerJoinEvent event) {

        GameSettings settings = this.model.getSettings();

        MutableSetting<Boolean> autoStartSetting = settings.getAllowAutoStartSetting();
        MutableSetting<Integer> minPlayerSetting = settings.getMinPlayerSetting();

        // Checking if the game can be started automatically.
        if(!autoStartSetting.getValue()) return;

        int players = this.model.getOnlinePlayers().size();

        // Min number of online players not reached.
        if(players < minPlayerSetting.getValue()) return;

        // Starting the game.
        try { this.controller.startGame();
        } catch (GameException e) { e.printStackTrace(); }
    }
}
