package com.github.syr0ws.universe.modules.chat;

import java.util.Collection;

public interface ChatService {

    void registerChat(Chat chat);

    void unregisterChat(Chat chat);

    boolean isRegistered(Chat chat);

    Collection<Chat> getChats();
}
