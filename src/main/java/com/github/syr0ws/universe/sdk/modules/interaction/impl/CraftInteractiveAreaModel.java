package com.github.syr0ws.universe.sdk.modules.interaction.impl;

import com.github.syr0ws.universe.sdk.modules.interaction.InteractiveArea;
import com.github.syr0ws.universe.sdk.modules.interaction.InteractiveAreaModel;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CraftInteractiveAreaModel implements InteractiveAreaModel {

    private final Set<InteractiveArea> area = new HashSet<>();

    @Override
    public void addArea(InteractiveArea area) {

        if(area == null)
            throw new IllegalArgumentException("InteractiveArea cannot be null.");

        this.area.add(area);
    }

    @Override
    public void removeArea(InteractiveArea area) {

        if(area == null)
            throw new IllegalArgumentException("InteractiveArea cannot be null.");

        this.area.remove(area);
    }

    @Override
    public boolean hasArea(InteractiveArea area) {
        return this.area.contains(area);
    }

    @Override
    public Collection<InteractiveArea> getArea() {
        return Collections.unmodifiableCollection(this.area);
    }
}
