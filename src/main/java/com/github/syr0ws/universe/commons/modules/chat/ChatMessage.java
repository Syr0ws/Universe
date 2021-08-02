package com.github.syr0ws.universe.commons.modules.chat;

import org.bukkit.entity.Player;

import java.util.Collection;

public interface ChatMessage {

    void setFormat(String format);

    void setReceivers(Collection<Player> players);

    Player getSender();

    String getMessage();

    String getFormat();

    Collection<Player> getReceivers();
}
