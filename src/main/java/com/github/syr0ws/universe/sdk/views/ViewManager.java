package com.github.syr0ws.universe.sdk.views;

import java.util.Collection;

public interface ViewManager<T extends View> {

    void addView(T view);

    void removeView(T view);

    void removeView(String id);

    boolean hasViews();

    T getView();

    T getView(String id);

    Collection<T> getViews();
}
