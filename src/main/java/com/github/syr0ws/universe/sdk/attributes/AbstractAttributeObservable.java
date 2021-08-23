package com.github.syr0ws.universe.sdk.attributes;

import com.github.syr0ws.universe.api.attributes.Attribute;
import com.github.syr0ws.universe.api.attributes.AttributeObservable;
import com.github.syr0ws.universe.api.attributes.AttributeObserver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class AbstractAttributeObservable implements AttributeObservable {

    private final List<AttributeObserver> observers = new ArrayList<>();

    @Override
    public void notifyChange(Attribute attribute) {
        new ArrayList<>(this.observers).stream()
                .filter(observer -> observer.observed().contains(attribute))
                .forEach(observer -> observer.onUpdate(attribute));
    }

    @Override
    public void addObserver(AttributeObserver observer) {

        if(this.observers.contains(observer))
            throw new UnsupportedOperationException("Observer already exists.");

        this.observers.add(observer);
    }

    @Override
    public void removeObserver(AttributeObserver observer) {
        this.observers.remove(observer);
    }

    @Override
    public boolean hasObserver(AttributeObserver observer) {
        return this.observers.contains(observer);
    }

    @Override
    public Collection<AttributeObserver> getObservers() {
        return Collections.unmodifiableList(this.observers);
    }
}
