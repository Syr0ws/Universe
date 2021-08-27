package com.github.syr0ws.universe.sdk.modules.lang.messages;

public interface MessageLoader {

    Message load(String key);

    boolean canLoad(String key);
}
