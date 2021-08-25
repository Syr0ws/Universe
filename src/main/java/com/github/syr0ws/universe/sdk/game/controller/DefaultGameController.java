package com.github.syr0ws.universe.sdk.game.controller;

import com.github.syr0ws.universe.api.attributes.Attribute;
import com.github.syr0ws.universe.api.attributes.AttributeObserver;
import com.github.syr0ws.universe.api.game.controller.GameController;
import com.github.syr0ws.universe.api.game.controller.cycle.GameCycle;
import com.github.syr0ws.universe.api.game.controller.cycle.GameCycleFactory;
import com.github.syr0ws.universe.api.game.mode.Mode;
import com.github.syr0ws.universe.api.game.mode.ModeManager;
import com.github.syr0ws.universe.api.game.mode.ModeType;
import com.github.syr0ws.universe.api.game.model.GameException;
import com.github.syr0ws.universe.api.game.model.GamePlayer;
import com.github.syr0ws.universe.api.game.model.GameState;
import com.github.syr0ws.universe.sdk.Game;
import com.github.syr0ws.universe.sdk.events.GamePlayerJoinEvent;
import com.github.syr0ws.universe.sdk.events.GamePlayerModeChangeEvent;
import com.github.syr0ws.universe.sdk.events.GamePlayerQuitEvent;
import com.github.syr0ws.universe.sdk.game.cycle.DefaultGameCycle;
import com.github.syr0ws.universe.sdk.game.mode.DefaultModeManager;
import com.github.syr0ws.universe.sdk.game.model.DefaultGameModel;
import com.github.syr0ws.universe.sdk.game.model.DefaultGamePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public abstract class DefaultGameController implements GameController, AttributeObserver {

    private final DefaultGameModel model;
    private final DefaultModeManager modeManager;

    private GameCycle cycle;

    public DefaultGameController(Game game, DefaultGameModel model) {

        if(game == null)
            throw new IllegalArgumentException("Game cannot be null.");

        if(model == null)
            throw new IllegalArgumentException("DefaultGameModel cannot be null.");

        this.model = model;
        this.modeManager = new DefaultModeManager();

        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new GameListener(), game);
    }

    public abstract GameCycleFactory getCycleFactory();

    @Override
    public void enable() {
        this.setGameState(GameState.WAITING);
    }

    @Override
    public void disable() {
        this.disableCurrentCycle();
    }

    @Override
    public void setMode(GamePlayer player, ModeType type) {

        if(player == null)
            throw new IllegalArgumentException("GamePlayer cannot be null.");

        if(type == null)
            throw new IllegalArgumentException("Mode cannot be null.");

        Mode mode = this.modeManager.getMode(type);

        DefaultGamePlayer gamePlayer = (DefaultGamePlayer) player;

        // Throwing an event.
        GamePlayerModeChangeEvent event = new GamePlayerModeChangeEvent(gamePlayer, mode);
        Bukkit.getPluginManager().callEvent(event);

        // Handling modes.
        ModeType old = gamePlayer.getModeType();

        // Removing old mode if it exists and is not the same as the new one.
        // Also checking that the player is online.
        if(old != null && player.isOnline())
            this.modeManager.getMode(old).disable(player.getPlayer());

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

        if(!this.model.isStarting() && !this.model.isStarted())
            throw new GameException("No game is currently started or starting.");

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

    protected void setGameState(GameState state) {

        // Disabling current cycle.
        this.disableCurrentCycle();

        GameCycleFactory factory = this.getCycleFactory();

        // As factory is defined in a child class, it's better to check that it is valid.
        if(factory == null)
            throw new NullPointerException("GameCycleFactory is null.");

        GameCycle cycle = factory.getGameCycle(state);

        // Enabling the new GameCycle.
        this.enableCycle(cycle);
    }

    private void onPlayerJoin(Player player) {

        DefaultGamePlayer gamePlayer;

        if(!this.model.exists(player.getUniqueId())) {

            // Creating the GamePlayer.
            gamePlayer = this.model.createPlayer(player);
            this.model.addPlayer(gamePlayer); // Storing player.

        } else {

            gamePlayer = this.model.getPlayer(player.getUniqueId());

            // Handling mode.
            Mode mode = this.modeManager.getMode(gamePlayer.getModeType());
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
        Mode mode = this.modeManager.getMode(gamePlayer.getModeType());
        mode.disable(player);
    }

    private void enableCycle(GameCycle cycle) {

        this.cycle = cycle;
        this.cycle.addObserver(this); // Adding observer.
        this.cycle.enable(); // Enabling the cycle.
    }

    private void disableCurrentCycle() {

        // Checking if a cycle is enabled.
        if(this.cycle == null) return;

        this.cycle.disable(); // Disabling the cycle.
        this.cycle.removeObserver(this); // Removing observer.
        this.cycle = null; // Avoid reuse.
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
