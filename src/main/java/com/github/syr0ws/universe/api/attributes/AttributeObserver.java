package com.github.syr0ws.universe.api.attributes;

import java.util.Collection;

public interface AttributeObserver {

    void onUpdate(Attribute attribute);

    Collection<Attribute> observed();
}
