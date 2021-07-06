package com.github.syr0ws.universe.modules.chat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Collection;
import java.util.Optional;

public class ChatListener implements Listener {

    private final ChatService service;

    public ChatListener(ChatService service) {

        if(service == null)
            throw new IllegalArgumentException("ChatService cannot be null.");

        this.service = service;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {

        // Always cancelling the event.
        event.setCancelled(true);

        ChatMessage message = new ChatMessage(event.getPlayer(), event.getMessage());

        // Finding a chat.
        Optional<Chat> optional = this.service.getChats().stream()
                .filter(chat -> chat.canSend(message))
                .findFirst();

        if(!optional.isPresent())
            throw new ChatException("No chat found.");

        Chat chat = optional.get();

        Collection<Player> receivers = chat.getReceivers(message);
        String format = chat.getFormat(message);

        // Sending message.
        receivers.forEach(player -> player.sendMessage(format));
    }
}
