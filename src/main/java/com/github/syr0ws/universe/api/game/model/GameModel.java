package com.github.syr0ws.universe.api.game.model;

import com.github.syr0ws.universe.api.attributes.AttributeObservable;
import com.github.syr0ws.universe.api.game.settings.GameSettings;
import org.bukkit.Location;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface GameModel extends AttributeObservable {

    void addTime();

    boolean isWaiting();

    boolean isStarting();

    boolean isRunning();

    boolean isFinished();

    boolean isStarted();

    boolean isValid(GamePlayer player);

    boolean exists(UUID uuid);

    boolean exists(GamePlayer player);

    int getTime();

    Location getSpawn();

    GameSettings getSettings();

    GameState getState();

    GamePlayer getPlayer(UUID uuid);

    Optional<? extends GamePlayer> getPlayer(String name);

    Collection<? extends GamePlayer> getPlayers();

    Collection<? extends GamePlayer> getOnlinePlayers();
}
