package com.github.syr0ws.universe.attributes;

import java.util.Collection;

public interface AttributeObserver {

    void onUpdate(Attribute attribute);

    Collection<Attribute> observed();
}
