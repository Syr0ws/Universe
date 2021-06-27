package com.github.syr0ws.universe.game.model.cycle;

public enum GameCycleState {

    WAITING, LOADED, STARTED, STOPPED, UNLOADED;

    public boolean isNext(GameCycleState state) {
        return this.ordinal() == state.ordinal() + 1;
    }
}
