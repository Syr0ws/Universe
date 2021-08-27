package com.github.syr0ws.universe.sdk.game.mode.types;

import com.github.syr0ws.universe.sdk.game.mode.DefaultModeType;
import com.github.syr0ws.universe.api.game.mode.ModeType;
import com.github.syr0ws.universe.api.game.model.GameModel;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class SpectatorMode extends DefaultGameMode {

    public SpectatorMode(GameModel model) {
        super(model);
    }

    @Override
    public void enable(Player player) {

        player.setHealth(20);
        player.setFoodLevel(20);
        player.setExp(0);
        player.setLevel(0);
        player.getInventory().clear();
        player.setGameMode(GameMode.SPECTATOR);
    }

    @Override
    public void disable(Player player) {

    }

    @Override
    public ModeType getType() {
        return DefaultModeType.SPECTATOR;
    }
}
