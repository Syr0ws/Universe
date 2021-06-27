package com.github.syr0ws.universe.game.model.cycle;

import com.github.syr0ws.universe.Game;
import com.github.syr0ws.universe.attributes.AbstractAttributeObservable;
import com.github.syr0ws.universe.attributes.Attribute;
import com.github.syr0ws.universe.listeners.ListenerManager;

public abstract class GameCycle extends AbstractAttributeObservable {

    private final Game game;
    private final ListenerManager listenerManager;
    private GameCycleState state;

    public GameCycle(Game game) {

        if(game == null)
            throw new IllegalArgumentException("Game cannot be null.");

        this.game = game;
        this.listenerManager = new ListenerManager(game);
        this.state = GameCycleState.WAITING;
    }

    private void setState(GameCycleState state) {

        if(!this.state.isNext(state))
            throw new GameCycleException(String.format("State '%s' is not the next state of '%s'.", state.name(), this.state.name()));

        this.state = state;
        this.notifyChange(GameCycleAttribute.CYCLE_STATE_CHANGE);
    }

    public void load() {
        this.setState(GameCycleState.LOADED);
    }

    public void unload() {
        this.setState(GameCycleState.UNLOADED);
    }

    public void start() {
        this.setState(GameCycleState.STARTED);
    }

    public void stop() {
        this.setState(GameCycleState.STOPPED);
    }

    public Game getGame() {
        return this.game;
    }

    public ListenerManager getListenerManager() {
        return this.listenerManager;
    }

    public GameCycleState getState() {
        return this.state;
    }

    public enum GameCycleAttribute implements Attribute {

        CYCLE_STATE_CHANGE
    }
}
