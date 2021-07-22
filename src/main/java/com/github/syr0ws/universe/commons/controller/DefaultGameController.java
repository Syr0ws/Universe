package com.github.syr0ws.universe.commons.controller;

import com.github.syr0ws.universe.commons.cycle.DefaultGameCycleFactory;
import com.github.syr0ws.universe.commons.mode.DefaultModeManager;
import com.github.syr0ws.universe.commons.mode.DefaultModeType;
import com.github.syr0ws.universe.commons.cycle.DefaultGameCycle;
import com.github.syr0ws.universe.commons.model.DefaultGameModel;
import com.github.syr0ws.universe.commons.model.DefaultGamePlayer;
import com.github.syr0ws.universe.sdk.attributes.Attribute;
import com.github.syr0ws.universe.sdk.attributes.AttributeObserver;
import com.github.syr0ws.universe.sdk.events.GamePlayerJoinEvent;
import com.github.syr0ws.universe.sdk.events.GamePlayerModeChangeEvent;
import com.github.syr0ws.universe.sdk.events.GamePlayerQuitEvent;
import com.github.syr0ws.universe.sdk.game.controller.GameController;
import com.github.syr0ws.universe.sdk.game.model.GameException;
import com.github.syr0ws.universe.sdk.game.model.GamePlayer;
import com.github.syr0ws.universe.sdk.game.model.GameState;
import com.github.syr0ws.universe.sdk.game.mode.Mode;
import com.github.syr0ws.universe.sdk.game.mode.ModeFactory;
import com.github.syr0ws.universe.sdk.game.mode.ModeManager;
import com.github.syr0ws.universe.sdk.game.mode.ModeType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public abstract class DefaultGameController implements GameController, AttributeObserver {

    private final DefaultGameModel model;
    private final DefaultModeManager modeManager;
    private final DefaultGameCycleFactory cycleFactory;

    public DefaultGameController(DefaultGameModel model) {

        if(model == null)
            throw new IllegalArgumentException("DefaultGameModel cannot be null.");

        this.model = model;
        this.modeManager = new DefaultModeManager();
        this.cycleFactory = new DefaultGameCycleFactory();
    }

    @Override
    public void setMode(GamePlayer player, ModeType type) {

        if(player == null)
            throw new IllegalArgumentException("GamePlayer cannot be null.");

        if(type == null)
            throw new IllegalArgumentException("Mode cannot be null.");

        Mode mode = ModeFactory.getMode(type);

        DefaultGamePlayer gamePlayer = (DefaultGamePlayer) player;

        // Throwing an event.
        GamePlayerModeChangeEvent event = new GamePlayerModeChangeEvent(gamePlayer, mode);
        Bukkit.getPluginManager().callEvent(event);

        // Handling modes.
        ModeType old = gamePlayer.getModeType();

        // Removing old mode if it exists and is not the same as the new one.
        // Also checking that the player is online.
        if(old != null && player.isOnline())
            ModeFactory.getMode(old).disable(player.getPlayer());

        gamePlayer.setModeType(mode.getType());

        // Setting new mode if the player is online.
        if(player.isOnline()) mode.enable(player.getPlayer());
    }

    @Override
    public void startGame() throws GameException {

        if(!this.model.isWaiting())
            throw new GameException("Game already started.");

        this.setGameState(GameState.STARTING);
    }

    @Override
    public void stopGame() throws GameException {

        if(!this.model.isStarted())
            throw new GameException("No game is currently started.");

        if(this.model.isFinished())
            throw new GameException("Game already finished.");

        GameState state = this.model.getState();

        if(state == GameState.STARTING) this.setGameState(GameState.WAITING);
        else this.setGameState(GameState.FINISHED);
    }

    @Override
    public void onUpdate(Attribute attribute) {

        Optional<GameState> optional = this.model.getState().getNext();
        optional.ifPresent(this::setGameState);
    }

    @Override
    public Collection<Attribute> observed() {
        return Collections.singleton(DefaultGameCycle.GameCycleAttribute.DONE);
    }

    private void onPlayerJoin(Player player) {

        DefaultGamePlayer gamePlayer;

        if(!this.model.exists(player.getUniqueId())) {

            ModeType type = this.model.isStarted() ? DefaultModeType.SPECTATOR : DefaultModeType.WAITING;

            // Creating the GamePlayer.
            gamePlayer = new DefaultGamePlayer(player, type);
            this.model.addPlayer(gamePlayer); // Storing player.

            // Setting mode.
            this.setMode(gamePlayer, type);

        } else {

            gamePlayer = this.model.getPlayer(player.getUniqueId());

            // Handling mode.
            Mode mode = ModeFactory.getMode(gamePlayer.getModeType());
            mode.enable(player);
        }

        // Throwing an event.
        GamePlayerJoinEvent event = new GamePlayerJoinEvent(gamePlayer);
        Bukkit.getPluginManager().callEvent(event);
    }

    private void onPlayerQuit(Player player) {

        DefaultGamePlayer gamePlayer = this.model.getPlayer(player.getUniqueId());

        // - Removing the GamePlayer if game is waiting because storing data is useless.
        //   It is not sure that the player will effectively rejoin the game again.
        //
        // - Removing the GamePlayer if he's not playing.
        //   Reduce the amount of data stored.
        if(this.model.isWaiting() || !gamePlayer.isPlaying()) this.model.removePlayer(gamePlayer);

        // Throwing an event.
        GamePlayerQuitEvent event = new GamePlayerQuitEvent(gamePlayer);
        Bukkit.getPluginManager().callEvent(event);

        // Disabling mode.
        Mode mode = ModeFactory.getMode(gamePlayer.getModeType());
        mode.disable(player);
    }

    private void setGameState(GameState state) {

        // Actions on the old GameCycle.
        DefaultGameCycle current = this.model.getCycle();

        // Disabling cycle only if it exists.
        if(current != null) this.disableCycle(current);

        // Actions on the new GameCycle.
        DefaultGameCycle cycle = this.cycleFactory.getGameCycle(state);
        this.enableCycle(cycle);
    }

    private void enableCycle(DefaultGameCycle cycle) {

        this.model.setCycle(cycle);

        cycle.load(); // Loading cycle.
        cycle.start(); // Starting cycle.
        cycle.addObserver(this); // Adding observer.
    }

    private void disableCycle(DefaultGameCycle cycle) {

        cycle.stop();
        cycle.unload(); // Unloading cycle.
        cycle.removeObserver(this); // Removing observer.
    }

    private class GameListener implements Listener {

        @EventHandler
        public void onPlayerJoin(PlayerJoinEvent event) {
            DefaultGameController.this.onPlayerJoin(event.getPlayer());
        }

        @EventHandler
        public void onPlayerQuit(PlayerQuitEvent event) {
            DefaultGameController.this.onPlayerQuit(event.getPlayer());
        }
    }

    public ModeManager getModeManager() {
        return this.modeManager;
    }
}
