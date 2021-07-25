package com.github.syr0ws.universe.commons.views;

import com.github.syr0ws.universe.sdk.views.View;
import com.github.syr0ws.universe.sdk.views.ViewException;
import com.github.syr0ws.universe.sdk.views.ViewManager;

import java.util.*;

public class DefaultViewManager<T extends View> implements ViewManager<T> {

    private final List<T> views = new ArrayList<>();

    @Override
    public void addView(T view) {

    }

    @Override
    public void removeView(T view) {

    }

    @Override
    public void removeView(String id) {

    }

    @Override
    public boolean hasViews() {
        return this.views.size() > 0;
    }

    @Override
    public T getView() {
        return null;
    }

    @Override
    public T getView(String id) {
        return this.views.stream()
                .filter(view -> view.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElseThrow(() -> new ViewException(String.format("No view found with id '%s'.", id)));
    }

    @Override
    public Collection<T> getViews() {
        return Collections.unmodifiableList(this.views);
    }
}
