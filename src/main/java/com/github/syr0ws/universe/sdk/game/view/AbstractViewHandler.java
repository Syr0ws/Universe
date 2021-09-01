package com.github.syr0ws.universe.sdk.game.view;

import com.github.syr0ws.universe.api.game.view.GameView;
import com.github.syr0ws.universe.api.game.view.ViewHandler;
import com.github.syr0ws.universe.sdk.Game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class AbstractViewHandler implements ViewHandler {

    private final Game game;
    private final List<GameView> views = new ArrayList<>();

    public AbstractViewHandler(Game game) {

        if(game == null)
            throw new IllegalArgumentException("Game cannot be null.");

        this.game = game;
    }

    @Override
    public void enable() {
        this.views.forEach(GameView::enable);
    }

    @Override
    public void disable() {
        this.views.forEach(GameView::disable);
    }

    @Override
    public void addView(GameView view) {

        if(view == null)
            throw new IllegalArgumentException("View cannot be null.");

        if(this.views.contains(view))
            throw new UnsupportedOperationException("View already registered.");

        this.views.add(view);
    }

    @Override
    public void removeView(GameView view) {

        if(view == null)
            throw new IllegalArgumentException("View cannot be null.");

        boolean removed = this.views.remove(view);
    }

    @Override
    public void removeViews() {
        List<GameView> copy = new ArrayList<>(this.views);
        copy.forEach(this::removeView);
    }

    @Override
    public boolean hasView(GameView view) {
        return this.views.contains(view);
    }

    @Override
    public Collection<GameView> getViews() {
        return Collections.unmodifiableCollection(this.views);
    }

    public Game getGame() {
        return this.game;
    }
}
