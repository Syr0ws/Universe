package com.github.syr0ws.universe.sdk.attributes;

import java.util.Collection;

public interface AttributeObserver {

    void onUpdate(Attribute attribute);

    Collection<Attribute> observed();
}
