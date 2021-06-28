package com.github.syr0ws.universe.game.model;

import com.github.syr0ws.universe.attributes.AttributeObservable;
import com.github.syr0ws.universe.game.model.cycle.GameCycle;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface GameModel extends AttributeObservable {

    GameCycle getCycle();

    boolean isWaiting();

    boolean isStarted();

    boolean isFinished();

    GamePlayer getPlayer(UUID uuid);

    Optional<? extends GamePlayer> getPlayer(String name);

    Collection<? extends GamePlayer> getPlayers();

    Collection<? extends GamePlayer> getOnlinePlayers();
}
