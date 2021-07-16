package com.github.syr0ws.universe.displays.impl;

import com.github.syr0ws.universe.displays.Display;
import com.github.syr0ws.universe.displays.DisplayFactory;
import com.github.syr0ws.universe.displays.DisplayService;
import com.github.syr0ws.universe.displays.loaders.ActionBarLoader;
import com.github.syr0ws.universe.displays.loaders.MessageLoader;
import com.github.syr0ws.universe.displays.loaders.SoundLoader;
import com.github.syr0ws.universe.displays.loaders.TitleLoader;
import com.github.syr0ws.universe.modules.lang.LangService;

import java.util.*;

public class SimpleDisplayService implements DisplayService {

    private final DisplayFactory factory;
    private final LangService service;

    private final Map<String, List<Display>> displays = new HashMap<>();

    public SimpleDisplayService() {
        this(null);
    }

    public SimpleDisplayService(LangService service) {
        this.factory = new SimpleDisplayFactory();
        this.service = service;

        // Registering loaders.
        this.registerLoaders();
    }

    @Override
    public void registerDisplay(String key, Display display) {

        key = getKey(key);

        if(display == null)
            throw new IllegalArgumentException("Display cannot be null.");

        List<Display> displays = this.displays.getOrDefault(key, new ArrayList<>());
        displays.add(display);

        this.displays.replace(key, displays);
    }

    @Override
    public void registerDisplay(String key, Collection<Display> displays) {

        if(displays == null)
            throw new IllegalArgumentException("Collection cannot be null.");

        key = getKey(key);

        List<Display> list = this.displays.getOrDefault(key, new ArrayList<>());
        list.addAll(displays);

        this.displays.replace(key, list);
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
    public Collection<String> getKeys() {
        return this.displays.keySet();
    }

    @Override
    public DisplayFactory getDisplayFactory() {
        return this.factory;
    }

    private String getKey(String key) {

        if(key == null)
            throw new IllegalArgumentException("Key cannot be null.");

        return key.toLowerCase();
    }

    private void registerLoaders() {
        this.factory.registerLoader("MESSAGE", new MessageLoader(this.service));
        this.factory.registerLoader("TITLE", new TitleLoader(this.service));
        this.factory.registerLoader("ACTION_BAR", new ActionBarLoader(this.service));
        this.factory.registerLoader("SOUND", new SoundLoader());
    }
}
