package com.github.syr0ws.universe.sdk.game.view;

import com.github.syr0ws.universe.api.attributes.Attribute;
import com.github.syr0ws.universe.api.displays.DisplayManager;
import com.github.syr0ws.universe.api.game.model.GameModel;
import com.github.syr0ws.universe.api.game.model.GameState;
import com.github.syr0ws.universe.api.game.view.GameView;
import com.github.syr0ws.universe.api.game.view.GameViewHandler;
import com.github.syr0ws.universe.api.game.view.GameStateViewHandler;
import com.github.syr0ws.universe.sdk.Game;
import com.github.syr0ws.universe.sdk.displays.DisplayUtils;
import com.github.syr0ws.universe.sdk.game.model.GameAttribute;

import java.util.*;

public abstract class AbstractGameViewHandler extends AbstractViewHandler implements GameViewHandler {

    private final Game game;
    private final GameModel model;
    private final DisplayManager manager;
    private final List<GameStateViewHandler> handlers = new ArrayList<>();

    private GameStateViewHandler handler;

    public AbstractGameViewHandler(Game game, GameModel model) {
        super(game);

        if(model == null)
            throw new IllegalArgumentException("GameModel cannot be null.");

        this.game = game;
        this.model = model;
        this.manager = DisplayUtils.getDisplayManager(this.game);
    }

    // Providing an empty method automatically called to register view handlers.
    // It avoids hesitations in the order to execute the super.enable() instruction.
    protected void registerViewHandlers() {}

    @Override
    public void enable() {
        super.enable(); // Enabling global views.

        // Adding the object to the list of observers.
        this.model.addObserver(this);

        // Registering view handlers.
        this.registerViewHandlers();

        // Setting the view handler for the current state.
        this.setViewHandler();
    }

    @Override
    public void disable() {
        super.disable(); // Disabling global views.

        // Removing the object from the observers.
        this.model.removeObserver(this);

        // Disabling the current view handler.
        this.disableCurrentViewHandler();

        // Removing view handlers because they're registered in the 'enable()' method.
        this.removeViewHandlers();
    }

    @Override
    public void addViewHandler(GameStateViewHandler handler) {

        if(handler == null)
            throw new IllegalArgumentException("StateViewHandler cannot be null.");

        this.handlers.removeIf(stored -> stored.getState().equals(handler.getState()));
        this.handlers.add(handler);
    }

    @Override
    public void removeViewHandler(GameStateViewHandler handler) {
        this.handlers.remove(handler);
    }

    @Override
    public void removeViewHandlers() {
        new ArrayList<>(this.handlers).forEach(this::removeViewHandler);
    }

    @Override
    public boolean hasViewHandler(GameStateViewHandler handler) {
        return this.handlers.contains(handler);
    }

    @Override
    public Optional<GameStateViewHandler> getViewHandler(GameState state) {
        return this.handlers.stream()
                .filter(handler -> handler.getState().equals(state))
                .findFirst();
    }

    @Override
    public DisplayManager getDisplayManager() {
        return this.manager;
    }

    @Override
    public void onUpdate(Attribute attribute) {
        this.setViewHandler();
    }

    @Override
    public Collection<Attribute> observed() {
        return Collections.singleton(GameAttribute.STATE_CHANGE);
    }

    public GameModel getModel() {
        return this.model;
    }

    private void setViewHandler() {

        this.disableCurrentViewHandler();

        GameState state = this.model.getState();

        // If there's a GameStateViewHandler object for the current game state,
        // enabling it.
        this.getViewHandler(state).ifPresent(handler -> {

            this.handler = handler;
            this.handler.enable();
        });
    }

    private void disableCurrentViewHandler() {

        // Checking if there's an active GameStateViewHandler.
        if(this.handler == null) return;

        this.handler.disable(); // Disabling GameStateViewHandler.
        this.handler = null; // Avoid reuse.
    }
}
