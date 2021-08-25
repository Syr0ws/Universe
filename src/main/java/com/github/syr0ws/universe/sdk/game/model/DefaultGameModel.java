package com.github.syr0ws.universe.sdk.game.model;

import com.github.syr0ws.universe.api.game.model.GameModel;
import com.github.syr0ws.universe.api.game.model.GamePlayer;
import com.github.syr0ws.universe.api.game.model.GameState;
import com.github.syr0ws.universe.api.game.settings.GameSettings;
import com.github.syr0ws.universe.api.settings.MutableSetting;
import com.github.syr0ws.universe.sdk.attributes.AbstractAttributeObservable;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

public abstract class DefaultGameModel extends AbstractAttributeObservable implements GameModel {

    private final GameSettings settings;
    private final Map<UUID, DefaultGamePlayer> players = new HashMap<>();

    private GameState state;
    private int time;

    public DefaultGameModel(GameSettings settings) {

        if(settings == null)
            throw new IllegalArgumentException("GameSettings cannot be null.");

        this.settings = settings;
    }

    public abstract DefaultGamePlayer createPlayer(Player player);

    public void addPlayer(GamePlayer player) {

        if(this.exists(player))
            throw new IllegalArgumentException("GamePlayer already exists.");

        if(!this.isValid(player))
            throw new IllegalArgumentException("Invalid GamePlayer.");

        this.players.put(player.getUUID(), (DefaultGamePlayer) player);
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

    public void setState(GameState state) {

        if(state == null)
            throw new IllegalArgumentException("GameState cannot be null.");

        this.state = state;
        this.notifyChange(GameAttribute.STATE_CHANGE);
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
    public boolean isStarting() {
        return this.getState() == GameState.STARTING;
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
    public boolean exists(UUID uuid) {
        return this.players.containsKey(uuid);
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
    public Location getSpawn() {
        MutableSetting<Location> setting = this.settings.getGameSpawnSetting();
        return setting.getValue();
    }

    @Override
    public GameSettings getSettings() {
        return this.settings;
    }

    @Override
    public GameState getState() {
        return this.state;
    }

    @Override
    public DefaultGamePlayer getPlayer(UUID uuid) {
        return this.players.get(uuid);
    }

    @Override
    public Optional<DefaultGamePlayer> getPlayer(String name) {
        return this.players.values().stream()
                .filter(player -> player.getName().equals(name))
                .findFirst();
    }

    @Override
    public Collection<DefaultGamePlayer> getPlayers() {
        return this.players.values();
    }

    @Override
    public Collection<DefaultGamePlayer> getOnlinePlayers() {
        return this.players.values().stream()
                .filter(GamePlayer::isOnline)
                .collect(Collectors.toList());
    }
}
