package com.github.syr0ws.universe.commons.settings;

import com.github.syr0ws.universe.sdk.settings.manager.SettingManager;
import com.github.syr0ws.universe.sdk.settings.types.LocationSetting;
import com.github.syr0ws.universe.sdk.settings.types.MutableSetting;
import org.bukkit.Location;

public class DefaultGameSettings implements GameSettings {

    private final SettingManager manager;

    public DefaultGameSettings(SettingManager manager) {

        if(manager == null)
            throw new IllegalArgumentException("SettingsManager cannot be null.");

        this.manager = manager;
    }

    // TODO Gérer le chargement des settings.

    public SettingManager getManager() {
        return this.manager;
    }

    @Override
    public MutableSetting<Integer> getMaxPlayersSetting() {
        return this.manager.getGenericSetting(GameSettingEnum.MAX_PLAYERS, Integer.class);
    }

    @Override
    public MutableSetting<Integer> getMaxSpectatorsSetting() {
        return this.manager.getGenericSetting(GameSettingEnum.MAX_SPECTATORS, Integer.class);
    }

    @Override
    public MutableSetting<Integer> getMaxGameDurationSetting() {
        return this.manager.getGenericSetting(GameSettingEnum.MAX_GAME_DURATION, Integer.class);
    }

    @Override
    public MutableSetting<Boolean> getAllowAutoStartSetting() {
        return this.manager.getGenericSetting(GameSettingEnum.ALLOW_AUTO_START, Boolean.class);
    }

    @Override
    public MutableSetting<Integer> getMinPlayersForAutoStartSetting() {
        return this.manager.getGenericSetting(GameSettingEnum.MIN_PLAYERS_FOR_AUTO_START, Integer.class);
    }

    @Override
    public MutableSetting<Integer> getMinPlayerSetting() {
        return this.manager.getGenericSetting(GameSettingEnum.MIN_PLAYERS, Integer.class);
    }

    @Override
    public MutableSetting<Boolean> getAllowWaitingChatSetting() {
        return this.manager.getGenericSetting(GameSettingEnum.ALLOW_WAITING_CHAT, Boolean.class);
    }

    @Override
    public MutableSetting<String> getWaitingChatFormatSetting() {
        return this.manager.getGenericSetting(GameSettingEnum.WAITING_CHAT_FORMAT, String.class);
    }

    @Override
    public MutableSetting<Boolean> getAllowGameChatSetting() {
        return this.manager.getGenericSetting(GameSettingEnum.ALLOW_GAME_CHAT, Boolean.class);
    }

    @Override
    public MutableSetting<String> getGameChatFormatSetting() {
        return this.manager.getGenericSetting(GameSettingEnum.GAME_CHAT_FORMAT, String.class);
    }

    @Override
    public MutableSetting<Boolean> getAllowSpectatorChatSetting() {
        return this.manager.getGenericSetting(GameSettingEnum.ALLOW_SPECTATOR_CHAT, Boolean.class);
    }

    @Override
    public MutableSetting<String> getSpectatorChatFormatSetting() {
        return this.manager.getGenericSetting(GameSettingEnum.SPECTATOR_CHAT_FORMAT, String.class);
    }

    @Override
    public MutableSetting<Integer> getStartingCycleDurationSetting() {
        return this.manager.getGenericSetting(GameSettingEnum.STARTING_CYCLE_DURATION, Integer.class);
    }

    @Override
    public MutableSetting<Location> getGameSpawnSetting() {
        return this.manager.getSetting(GameSettingEnum.GAME_SPAWN, LocationSetting.class);
    }
}
