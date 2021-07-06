package com.github.syr0ws.universe.modules.chat;

import org.bukkit.entity.Player;

import java.util.Collection;

public interface Chat {

    boolean canSend(ChatMessage message);

    String getFormat(ChatMessage message);

    Collection<Player> getReceivers(ChatMessage message);
}
