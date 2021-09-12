package com.github.syr0ws.universe.sdk.modules.interaction;

public interface InteractiveLoader<T> {

    Interactive<T> load(String type) throws InteractiveException;

    InteractionType getType();
}
