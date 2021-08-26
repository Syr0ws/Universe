package com.github.syr0ws.universe.sdk.game.cycle;

import com.github.syr0ws.universe.api.attributes.Attribute;
import com.github.syr0ws.universe.api.game.controller.GameController;
import com.github.syr0ws.universe.api.game.controller.cycle.GameCycle;
import com.github.syr0ws.universe.api.game.controller.cycle.GameCycleException;
import com.github.syr0ws.universe.api.game.model.GameModel;
import com.github.syr0ws.universe.sdk.Game;
import com.github.syr0ws.universe.sdk.attributes.AbstractAttributeObservable;
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
    public void enable() {
        this.registerListeners(this.listenerManager);
        this.setState(GameCycleState.ENABLED);
    }

    @Override
    public void disable() {
        this.listenerManager.removeListeners();
        this.setState(GameCycleState.DISABLED);
    }

    public void registerListeners(ListenerManager manager) {}

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
    }

    private enum GameCycleState {

        WAITING, ENABLED, DISABLED;

        public boolean isNext(GameCycleState state) {
            return state.ordinal() == this.ordinal() + 1;
        }
    }

    public enum GameCycleAttribute implements Attribute {

        DONE;
    }
}
