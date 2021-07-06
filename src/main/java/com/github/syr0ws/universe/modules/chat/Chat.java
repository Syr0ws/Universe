package com.github.syr0ws.universe.modules.chat;

public interface Chat {

    void onChat(ChatMessage message);

    boolean canSend(ChatMessage message);
}
