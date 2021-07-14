package com.github.syr0ws.universe.modules.lang.messages;

public interface MessageLoader {

    Message load(String key);

    boolean canLoad(String key);
}
