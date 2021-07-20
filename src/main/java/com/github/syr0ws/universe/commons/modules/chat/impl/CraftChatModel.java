package com.github.syr0ws.universe.commons.modules.chat.impl;

import com.github.syr0ws.universe.commons.modules.chat.Chat;
import com.github.syr0ws.universe.commons.modules.chat.ChatException;
import com.github.syr0ws.universe.commons.modules.chat.ChatModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CraftChatModel implements ChatModel {

    private final List<Chat> chats = new ArrayList<>();

    @Override
    public void registerChat(Chat chat) {

        if(this.chats.contains(chat))
            throw new ChatException("Chat already registered.");

        this.chats.add(chat);
    }

    @Override
    public void unregisterChat(Chat chat) {

        if(!this.chats.contains(chat))
            throw new ChatException("Chat not registered.");

        this.chats.remove(chat);
    }

    @Override
    public boolean isRegistered(Chat chat) {
        return this.chats.contains(chat);
    }

    @Override
    public Collection<Chat> getChats() {
        return Collections.unmodifiableList(this.chats);
    }
}
