package com.github.syr0ws.universe.displays;

import java.util.Collection;

public interface DisplayService {

    void registerDisplay(String key, Display display);

    void registerDisplay(String key, Collection<Display> displays);

    void unregisterDisplay(String key);

    Collection<Display> getDisplays(String key);

    Collection<String> getKeys();

    DisplayFactory getDisplayFactory();
}
