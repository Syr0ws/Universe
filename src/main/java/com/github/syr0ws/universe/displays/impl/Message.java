package com.github.syr0ws.universe.displays.impl;

import com.github.syr0ws.universe.displays.TextDisplay;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Message extends TextDisplay {

    private final String text;

    public Message(String text) {

        if(text == null)
            throw new IllegalArgumentException("Text cannot be null.");

        this.text = text;
    }

    public void displayTo(CommandSender sender) {
        String message = super.format(this.text);
        sender.sendMessage(message);
    }

    @Override
    public void displayTo(Player player) {
        String message = super.format(this.text);
        player.sendMessage(message);
    }

    @Override
    public void displayAll() {
        String message = super.format(this.text);
        Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(message));
    }

    public String getText() {
        return super.format(this.text);
    }
}
