package com.github.syr0ws.universe.commons.settings;

import com.github.syr0ws.universe.sdk.settings.types.MutableSetting;
import org.bukkit.Location;

public interface GameSettings {

    MutableSetting<Integer> getMaxPlayersSetting();

    MutableSetting<Integer> getMaxSpectatorsSetting();

    MutableSetting<Integer> getMaxGameDurationSetting();

    MutableSetting<Boolean> getAllowAutoStartSetting();

    MutableSetting<Integer> getMinPlayersForAutoStartSetting();

    MutableSetting<Integer> getMinPlayerSetting();

    MutableSetting<Boolean> getAllowWaitingChatSetting();

    MutableSetting<String> getWaitingChatFormatSetting();

    MutableSetting<Boolean> getAllowGameChatSetting();

    MutableSetting<String> getGameChatFormatSetting();

    MutableSetting<Boolean> getAllowSpectatorChatSetting();

    MutableSetting<String> getSpectatorChatFormatSetting();

    MutableSetting<Integer> getStartingCycleDurationSetting();

    MutableSetting<Location> getGameSpawnSetting();
}
