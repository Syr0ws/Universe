package com.github.syr0ws.universe.sdk.modules.interaction;

public interface Interactive<T> {

    void setInteractive(boolean interactive);

    boolean isInteractive();

    boolean is(T element);

    T get();

    Class<T> getParamClass();

    InteractionType getType();
}
