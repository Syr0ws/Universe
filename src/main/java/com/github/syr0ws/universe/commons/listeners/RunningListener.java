package com.github.syr0ws.universe.commons.listeners;

import com.github.syr0ws.universe.commons.mode.DefaultModeType;
import com.github.syr0ws.universe.commons.settings.GameSettings;
import com.github.syr0ws.universe.sdk.events.GamePlayerJoinEvent;
import com.github.syr0ws.universe.sdk.game.controller.GameController;
import com.github.syr0ws.universe.sdk.game.model.GameModel;
import com.github.syr0ws.universe.sdk.game.model.GamePlayer;
import com.github.syr0ws.universe.sdk.settings.types.MutableSetting;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class RunningListener implements Listener {

    private final GameModel model;
    private final GameController controller;

    public RunningListener(GameModel model, GameController controller) {

        if(model == null)
            throw new IllegalArgumentException("GameModel cannot be null.");

        if(controller == null)
            throw new IllegalArgumentException("GameController cannot be null.");

        this.model = model;
        this.controller = controller;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerPreLogin(AsyncPlayerPreLoginEvent event) {

        GameSettings settings = this.model.getSettings();
        MutableSetting<Integer> setting = settings.getMaxSpectatorsSetting();

        GamePlayer player = this.model.getPlayer(event.getUniqueId());

        // If the player exists and is playing, this setting doesn't be applied.
        if(player != null && player.isPlaying()) return;

        long spectators = this.model.getOnlinePlayers().stream()
                .filter(online -> online.getModeType().equals(DefaultModeType.SPECTATOR))
                .count();

        // Max number of online players not reached.
        if(spectators < setting.getValue()) return;

        // Kicking the player.
        event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_FULL);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onGamePlayerJoin(GamePlayerJoinEvent event) {

        GamePlayer gamePlayer = event.getGamePlayer();

        if(!gamePlayer.isPlaying()) this.controller.setMode(gamePlayer, DefaultModeType.SPECTATOR);
    }
}
