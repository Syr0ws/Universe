package com.github.syr0ws.universe.api.displays;

import java.util.Collection;
import java.util.Map;

public interface DisplayManager {

    void loadDisplays(String path);

    void loadDisplays(Collection<String> paths);

    void registerDisplays(String key, Display display);

    void registerDisplays(String key, Collection<Display> displays);

    void unregisterDisplay(String key);

    Collection<Display> getDisplays(String key);

    Map<String, Collection<Display>> getDisplays();
}
