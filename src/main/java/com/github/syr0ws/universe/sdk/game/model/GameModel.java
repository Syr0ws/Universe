package com.github.syr0ws.universe.sdk.game.model;

import com.github.syr0ws.universe.commons.settings.GameSettings;
import com.github.syr0ws.universe.sdk.attributes.AttributeObservable;
import com.github.syr0ws.universe.sdk.game.model.cycle.GameCycle;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface GameModel extends AttributeObservable {

    void addTime();

    boolean isWaiting();

    boolean isRunning();

    boolean isFinished();

    boolean isStarted();

    boolean isValid(GamePlayer player);

    boolean exists(UUID uuid);

    boolean exists(GamePlayer player);

    int getTime();

    GameSettings getSettings();

    GameState getState();

    GameCycle getCycle();

    GamePlayer getPlayer(UUID uuid);

    Optional<? extends GamePlayer> getPlayer(String name);

    Collection<? extends GamePlayer> getPlayers();

    Collection<? extends GamePlayer> getOnlinePlayers();
}
