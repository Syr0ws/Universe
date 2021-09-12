package com.github.syr0ws.universe.sdk.modules.interaction;

import java.util.Collection;

public interface InteractiveAreaModel {

    void addArea(InteractiveArea area);

    void removeArea(InteractiveArea area);

    boolean hasArea(InteractiveArea area);

    Collection<InteractiveArea> getArea();
}
