package com.github.syr0ws.universe.sdk.modules.chat.impl;

import com.github.syr0ws.universe.sdk.modules.chat.ChatMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class CraftChatMessage implements ChatMessage {

    private final Player player;
    private final String message;

    private String format;
    private Collection<Player> receivers;

    public CraftChatMessage(Player player, String message) {

        if(player == null)
            throw new IllegalArgumentException("Player cannot be null.");

        if(message == null || message.isEmpty())
            throw new IllegalArgumentException("Message cannot be null or empty.");

        this.player = player;
        this.message = message;

        this.format = String.format("%s : %s", player.getName(), message);

        this.receivers = new ArrayList<>();
        this.receivers.addAll(Bukkit.getOnlinePlayers());
    }

    @Override
    public void setFormat(String format) {

        if(format == null || format.isEmpty())
            throw new IllegalArgumentException("Format cannot be null or empty.");

        this.format = format;
    }

    @Override
    public void setReceivers(Collection<Player> receivers) {

        if(receivers == null)
            throw new IllegalArgumentException("Receivers cannot be null.");

        this.receivers = receivers;
    }

    @Override
    public Player getSender() {
        return this.player;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String getFormat() {
        return this.format;
    }

    @Override
    public Collection<Player> getReceivers() {
        return Collections.unmodifiableCollection(this.receivers);
    }
}
