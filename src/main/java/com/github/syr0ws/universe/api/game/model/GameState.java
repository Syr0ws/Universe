package com.github.syr0ws.universe.api.game.model;

import java.util.Optional;

public enum GameState {

    LOADING, WAITING, STARTING, RUNNING, FINISHED;

    public Optional<GameState> getNext() {

        GameState[] states = values();
        int index = this.ordinal() + 1;

        return index < states.length ? Optional.of(states[index]) : Optional.empty();
    }
}
