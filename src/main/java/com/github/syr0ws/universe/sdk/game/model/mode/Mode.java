package com.github.syr0ws.universe.sdk.game.model.mode;

import org.bukkit.entity.Player;

public interface Mode {

    void enable(Player player);

    void disable(Player player);

    ModeType getType();
}
