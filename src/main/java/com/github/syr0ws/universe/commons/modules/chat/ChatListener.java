package com.github.syr0ws.universe.commons.modules.chat;

import com.github.syr0ws.universe.commons.modules.chat.impl.CraftChatMessage;
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

        // A cancelled event means that the message must not be sent even if a chat can be used.
        if(event.isCancelled()) return;

        // Always cancelling the event.
        event.setCancelled(true);

        CraftChatMessage message = new CraftChatMessage(event.getPlayer(), event.getMessage());

        // Finding a chat.
        Optional<Chat> optional = this.service.getChats().stream()
                .filter(chat -> chat.canSend(message))
                .max(Comparator.comparingInt(chat -> chat.getPriority().ordinal()));

        // Checking if a chat has been found.
        if(!optional.isPresent()) return;

        Chat chat = optional.get();
        chat.onChat(message);

        // Sending the message.
        message.getReceivers().forEach(player -> player.sendMessage(message.getFormat()));
    }
}
