package com.github.syr0ws.universe.displays.impl;

import com.github.syr0ws.universe.displays.Display;
import com.github.syr0ws.universe.displays.DisplayException;
import com.github.syr0ws.universe.displays.DisplayManager;
import com.github.syr0ws.universe.displays.dao.DisplayDAO;

import java.util.*;

public class SimpleDisplayManager implements DisplayManager {

    private final DisplayDAO dao;

    private final Map<String, List<Display>> displays = new HashMap<>();

    public SimpleDisplayManager(DisplayDAO dao) {

        if(dao == null)
            throw new IllegalArgumentException("DisplayDAO cannot be null.");

        this.dao = dao;
    }

    @Override
    public void loadDisplays(String path) {

        try {

            Collection<Display> displays = this.dao.getDisplays(path);
            this.registerDisplays(path, displays);

        } catch (DisplayException e) { e.printStackTrace(); }
    }

    @Override
    public void loadDisplays(Collection<String> paths) {

        for(String path : paths) {

            try {

                Collection<Display> displays = this.dao.getDisplays(path);
                this.registerDisplays(path, displays);

            } catch (DisplayException e) { e.printStackTrace(); }
        }
    }

    @Override
    public void registerDisplays(String key, Display display) {

        key = getKey(key);

        if(display == null)
            throw new IllegalArgumentException("Display cannot be null.");

        List<Display> displays = this.displays.getOrDefault(key, new ArrayList<>());
        displays.add(display);

        this.displays.put(key, displays);
    }

    @Override
    public void registerDisplays(String key, Collection<Display> displays) {

        if(displays == null)
            throw new IllegalArgumentException("Collection cannot be null.");

        key = getKey(key);

        List<Display> list = this.displays.getOrDefault(key, new ArrayList<>());
        list.addAll(displays);

        this.displays.put(key, list);
    }

    @Override
    public void unregisterDisplay(String key) {
        key = getKey(key);
        this.displays.remove(key);
    }

    @Override
    public Collection<Display> getDisplays(String key) {
        key = getKey(key);
        return this.displays.getOrDefault(key, new ArrayList<>());
    }

    @Override
    public Map<String, Collection<Display>> getDisplays() {
        return Collections.unmodifiableMap(this.displays);
    }

    private String getKey(String key) {

        if(key == null)
            throw new IllegalArgumentException("Key cannot be null.");

        return key.toLowerCase();
    }
}
