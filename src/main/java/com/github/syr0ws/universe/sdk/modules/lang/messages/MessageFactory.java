package com.github.syr0ws.universe.sdk.modules.lang.messages;

import java.util.Optional;

public interface MessageFactory {

    Optional<Message> getMessage(String key);
}
