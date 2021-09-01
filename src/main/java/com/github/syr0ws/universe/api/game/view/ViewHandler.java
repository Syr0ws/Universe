package com.github.syr0ws.universe.api.game.view;

import java.util.Collection;

public interface ViewHandler {

    void enable();

    void disable();

    void addView(GameView view);

    void removeView(GameView view);

    void removeViews();

    boolean hasView(GameView view);

    Collection<GameView> getViews();
}
