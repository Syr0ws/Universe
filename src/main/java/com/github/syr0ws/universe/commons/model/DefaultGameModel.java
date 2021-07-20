package com.github.syr0ws.universe.commons.model;

import com.github.syr0ws.universe.commons.settings.GameSettings;
import com.github.syr0ws.universe.sdk.attributes.AbstractAttributeObservable;
import com.github.syr0ws.universe.sdk.game.model.GameState;
import com.github.syr0ws.universe.sdk.game.model.GameModel;
import com.github.syr0ws.universe.sdk.game.model.GamePlayer;
import com.github.syr0ws.universe.sdk.game.model.cycle.GameCycle;

import java.util.*;
import java.util.stream.Collectors;

public abstract class DefaultGameModel extends AbstractAttributeObservable implements GameModel {

    private final GameSettings settings;
    private final Map<UUID, GamePlayer> players = new HashMap<>();

    private GameCycle cycle;
    private int time;

    public DefaultGameModel(GameSettings settings) {

        if(settings == null)
            throw new IllegalArgumentException("GameSettings cannot be null.");

        this.settings = settings;
    }

    public void addPlayer(GamePlayer player) {

        if(this.exists(player))
            throw new IllegalArgumentException("GamePlayer already exists.");

        if(!this.isValid(player))
            throw new IllegalArgumentException("Invalid GamePlayer.");

        this.players.put(player.getUUID(), player);
        this.notifyChange(GameAttribute.GAME_PLAYER_CHANGE);
    }

    public void removePlayer(GamePlayer player) {

        if(!this.exists(player))
            throw new IllegalArgumentException("GamePlayer doesn't exist.");

        if(!this.isValid(player))
            throw new IllegalArgumentException("Invalid GamePlayer.");

        if(player.isPlaying())
            throw new IllegalArgumentException("Cannot remove a GamePlayer while he is playing.");

        this.players.remove(player.getUUID());
        this.notifyChange(GameAttribute.GAME_PLAYER_CHANGE);
    }

    public void setCycle(GameCycle cycle) {

        if(cycle == null)
            throw new IllegalArgumentException("GameCycle cannot be null.");

        this.cycle = cycle;
        this.notifyChange(GameAttribute.STATE_CHANGE);
        this.notifyChange(GameAttribute.CYCLE_CHANGE);
    }

    @Override
    public void addTime() {
        this.time++;
        this.notifyChange(GameAttribute.TIME_CHANGE);
    }

    @Override
    public boolean isWaiting() {
        return this.getState() == GameState.WAITING;
    }

    @Override
    public boolean isRunning() {
        return this.getState() == GameState.RUNNING;
    }

    @Override
    public boolean isFinished() {
        return this.getState() == GameState.FINISHED;
    }

    @Override
    public boolean isStarted() {
        return this.getState().ordinal() >= GameState.RUNNING.ordinal();
    }

    @Override
    public boolean exists(GamePlayer player) {
        return this.players.containsKey(player.getUUID());
    }

    @Override
    public int getTime() {
        return this.time;
    }

    @Override
    public GameSettings getSettings() {
        return this.settings;
    }

    @Override
    public GameState getState() {
        return this.cycle.getGameState();
    }

    @Override
    public GameCycle getCycle() {
        return this.cycle;
    }

    @Override
    public GamePlayer getPlayer(UUID uuid) {
        return this.players.get(uuid);
    }

    @Override
    public Optional<? extends GamePlayer> getPlayer(String name) {
        return this.players.values().stream()
                .filter(player -> player.getName().equals(name))
                .findFirst();
    }

    @Override
    public Collection<? extends GamePlayer> getPlayers() {
        return this.players.values();
    }

    @Override
    public Collection<? extends GamePlayer> getOnlinePlayers() {
        return this.players.values().stream()
                .filter(GamePlayer::isOnline)
                .collect(Collectors.toList());
    }
}
