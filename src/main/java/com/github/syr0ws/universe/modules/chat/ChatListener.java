package com.github.syr0ws.universe.modules.chat;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Comparator;
import java.util.Optional;

public class ChatListener implements Listener {

    private final ChatModel service;

    public ChatListener(ChatModel service) {

        if(service == null)
            throw new IllegalArgumentException("ChatModel cannot be null.");

        this.service = service;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerChat(AsyncPlayerChatEvent event) {

        // Always cancelling the event.
        event.setCancelled(true);

        ChatMessage message = new ChatMessage(event.getPlayer(), event.getMessage());

        // Finding a chat.
        Optional<Chat> optional = this.service.getChats().stream()
                .filter(chat -> chat.canSend(message))
                .max(Comparator.comparingInt(chat -> chat.getPriority().ordinal()));

        // If a chat has been found, handling message.
        optional.ifPresent(chat -> chat.onChat(message));
    }
}
