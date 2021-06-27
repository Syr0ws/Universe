package com.github.syr0ws.universe.attributes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AttributeObservable {

    private final List<AttributeObserver> observers = new ArrayList<>();

    public void notifyChange(Attribute attribute) {
        new ArrayList<>(this.observers).stream()
                .filter(observer -> observer.observed().contains(attribute))
                .forEach(observer -> observer.onUpdate(attribute));
    }

    public void addObserver(AttributeObserver observer) {

        if(this.observers.contains(observer))
            throw new UnsupportedOperationException("Observer already exists.");

        this.observers.add(observer);
    }

    public void removeObserver(AttributeObserver observer) {
        this.observers.remove(observer);
    }

    public boolean hasObserver(AttributeObserver observer) {
        return this.observers.contains(observer);
    }

    public Collection<AttributeObserver> getObservers() {
        return Collections.unmodifiableList(this.observers);
    }
}
