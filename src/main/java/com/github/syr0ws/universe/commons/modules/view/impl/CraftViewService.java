package com.github.syr0ws.universe.commons.modules.view.impl;

import com.github.syr0ws.universe.commons.modules.view.*;

import java.util.*;

public class CraftViewService implements ViewService {

    private final List<ViewHandler<?>> handlers = new ArrayList<>();

    @Override
    public void addViewHandler(ViewHandler<?> handler) {

        if(handler == null)
            throw new IllegalArgumentException("ViewHandler cannot be null.");

        if(handler.getViewType() == null)
            throw new ViewException("ViewHandler type cannot be null.");

        if(this.hasViewHandler(handler.getViewType()))
            throw new ViewException("A ViewHandler with this type already exists.");

        handler.enable();

        this.handlers.add(handler);
    }

    @Override
    public void removeViewHandler(ViewType type) {

        ViewHandler<?> handler = this.handlers.stream()
                .filter(stored -> stored.getViewType().equals(type)).findFirst()
                .orElseThrow(() -> new ViewException("No ViewHandler found."));

        this.onRemove(handler);
        this.handlers.remove(handler);
    }

    @Override
    public void removeViewHandlers() {

        Iterator<ViewHandler<?>> iterator = this.handlers.iterator();

        while(iterator.hasNext()) {

            ViewHandler<?> handler = iterator.next();

            this.onRemove(handler);

            iterator.remove();
        }
    }

    @Override
    public boolean hasViewHandler(ViewType type) {
        return this.handlers.stream().anyMatch(handler -> handler.getViewType().equals(type));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends View> ViewHandler<T> getViewHandler(ViewType type, Class<T> clazz) {

        if(type == null)
            throw new IllegalArgumentException("ViewType cannot be null.");

        if(clazz == null)
            throw new IllegalArgumentException("ViewHandler class cannot be null.");

        ViewHandler<?> handler = this.handlers.stream()
                .filter(stored -> stored.getViewType().equals(type)).findFirst()
                .orElseThrow(() -> new ViewException("No ViewHandler found."));

        if(!clazz.equals(handler.getViewClass()))
            throw new ClassCastException("Incompatible ViewHandler class.");

        return (ViewHandler<T>) handler;
    }

    @Override
    public Collection<ViewHandler<?>> getViewHandlers() {
        return Collections.unmodifiableList(this.handlers);
    }

    private void onRemove(ViewHandler<?> handler) {
        handler.removeViews(); // Removing views stored in the handler.
        handler.disable(); // Disabling the handler.
    }
}
