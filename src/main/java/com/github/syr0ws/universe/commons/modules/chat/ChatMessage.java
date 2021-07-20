package com.github.syr0ws.universe.commons.modules.chat;

import org.bukkit.entity.Player;

public class ChatMessage {

    private final Player player;
    private final String message;

    public ChatMessage(Player player, String message) {

        if(player == null)
            throw new IllegalArgumentException("Player cannot be null.");

        if(message == null || message.isEmpty())
            throw new IllegalArgumentException("Message cannot be null or empty.");

        this.player = player;
        this.message = message;
    }

    public Player getPlayer() {
        return this.player;
    }

    public String getMessage() {
        return this.message;
    }
}
