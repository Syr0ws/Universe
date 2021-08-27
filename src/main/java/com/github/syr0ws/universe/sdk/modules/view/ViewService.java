package com.github.syr0ws.universe.sdk.modules.view;

import java.util.Collection;

public interface ViewService {

    void addViewHandler(ViewHandler<?> handler);

    void removeViewHandler(ViewType type);

    void removeViewHandlers();

    boolean hasViewHandler(ViewType type);

    <T extends View> ViewHandler<T> getViewHandler(ViewType type, Class<T> clazz);

    Collection<ViewHandler<?>> getViewHandlers();
}
