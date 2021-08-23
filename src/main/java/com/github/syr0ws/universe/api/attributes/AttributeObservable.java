package com.github.syr0ws.universe.api.attributes;

import java.util.Collection;

public interface AttributeObservable {

    void notifyChange(Attribute attribute);

    void addObserver(AttributeObserver observer);

    void removeObserver(AttributeObserver observer);

    boolean hasObserver(AttributeObserver observer);

    Collection<AttributeObserver> getObservers();
}
