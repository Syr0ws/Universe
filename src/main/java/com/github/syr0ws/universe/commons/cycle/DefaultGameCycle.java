package com.github.syr0ws.universe.commons.cycle;

import com.github.syr0ws.universe.sdk.Game;
import com.github.syr0ws.universe.sdk.attributes.AbstractAttributeObservable;
import com.github.syr0ws.universe.sdk.game.controller.GameController;
import com.github.syr0ws.universe.sdk.game.cycle.GameCycle;
import com.github.syr0ws.universe.sdk.game.cycle.GameCycleAttribute;
import com.github.syr0ws.universe.sdk.game.cycle.GameCycleException;
import com.github.syr0ws.universe.sdk.game.cycle.GameCycleState;
import com.github.syr0ws.universe.sdk.game.model.GameModel;
import com.github.syr0ws.universe.sdk.listeners.ListenerManager;

public abstract class DefaultGameCycle extends AbstractAttributeObservable implements GameCycle {

    private final Game game;
    private final GameModel model;
    private final GameController controller;

    private final ListenerManager listenerManager;
    private GameCycleState state;

    public DefaultGameCycle(Game game, GameModel model, GameController controller) {

        if(game == null)
            throw new IllegalArgumentException("Game cannot be null.");

        if(model == null)
            throw new IllegalArgumentException("GameModel cannot be null.");

        if(controller == null)
            throw new IllegalArgumentException("GameController cannot be null.");

        this.game = game;
        this.model = model;
        this.controller = controller;

        this.listenerManager = new ListenerManager(game);
        this.state = GameCycleState.WAITING;
    }

    @Override
    public void load() {
        this.setState(GameCycleState.LOADED);
    }

    @Override
    public void unload() {

        // Automatically remove listeners.
        this.listenerManager.removeListeners();

        this.setState(GameCycleState.UNLOADED);
    }

    @Override
    public void start() {
        this.setState(GameCycleState.STARTED);
    }

    @Override
    public void stop() {
        this.setState(GameCycleState.STOPPED);
    }

    @Override
    public GameCycleState getCycleState() {
        return this.state;
    }

    protected void done() {
        this.notifyChange(GameCycleAttribute.DONE);
    }

    public Game getGame() {
        return this.game;
    }

    public GameModel getModel() {
        return this.model;
    }

    public GameController getController() {
        return this.controller;
    }

    public ListenerManager getListenerManager() {
        return this.listenerManager;
    }

    private void setState(GameCycleState state) {

        if(!this.state.isNext(state))
            throw new GameCycleException(String.format("State '%s' is not the next state of '%s'.", state.name(), this.state.name()));

        this.state = state;
        this.notifyChange(GameCycleAttribute.STATE_CHANGE);
    }
}
