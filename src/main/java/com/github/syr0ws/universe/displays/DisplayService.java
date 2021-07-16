package com.github.syr0ws.universe.displays;

import java.util.Collection;

public interface DisplayService {

    void loadDisplays(String path);

    void loadDisplays(Collection<String> paths);

    void registerDisplays(String key, Display display);

    void registerDisplays(String key, Collection<Display> displays);

    void unregisterDisplay(String key);

    Collection<Display> getDisplays(String key);

    Collection<String> getKeys();

    DisplayFactory getDisplayFactory();
}
