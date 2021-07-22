package com.github.syr0ws.universe.commons.listeners;

import com.github.syr0ws.universe.commons.settings.GameSettings;
import com.github.syr0ws.universe.sdk.events.GamePlayerJoinEvent;
import com.github.syr0ws.universe.sdk.game.controller.GameController;
import com.github.syr0ws.universe.sdk.game.model.GameException;
import com.github.syr0ws.universe.sdk.game.model.GameModel;
import com.github.syr0ws.universe.sdk.settings.types.MutableSetting;
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
        MutableSetting<Integer> setting = settings.getMinPlayerSetting();

        int players = this.model.getOnlinePlayers().size();

        // Min number of online players not reached.
        if(players < setting.getValue()) return;

        // Starting the game.
        try { this.controller.startGame();
        } catch (GameException e) { e.printStackTrace(); }
    }
}
