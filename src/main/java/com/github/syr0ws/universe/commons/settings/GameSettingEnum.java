package com.github.syr0ws.universe.commons.settings;

import com.github.syr0ws.universe.commons.placeholders.PlaceholderEnum;
import com.github.syr0ws.universe.sdk.settings.Setting;
import com.github.syr0ws.universe.sdk.settings.SettingType;
import com.github.syr0ws.universe.sdk.settings.types.LocationSetting;
import com.github.syr0ws.universe.sdk.settings.types.SimpleConfigSetting;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public enum GameSettingEnum implements SettingType {

    MIN_PLAYERS {
        @Override
        public Setting<?> getSetting() {
            return new SimpleConfigSetting
                    .Builder<>("minPlayers", 2, "min-players", Integer.class)
                    .withFilter(value -> value >= 2)
                    .build();
        }
    },

    MIN_PLAYERS_FOR_AUTO_START {
        @Override
        public Setting<?> getSetting() {
            return new SimpleConfigSetting
                    .Builder<>("minPlayersForAutoStart", 2, "min-players-for-auto-start", Integer.class)
                    .withFilter(value -> value >= 2)
                    .build();
        }
    },

    MAX_PLAYERS {
        @Override
        public Setting<?> getSetting() {
            return new SimpleConfigSetting
                    .Builder<>("maxPlayers", 16, "max-players", Integer.class)
                    .withFilter(value -> value >= 2)
                    .build();
        }
    },

    MAX_SPECTATORS {
        @Override
        public Setting<?> getSetting() {
            return new SimpleConfigSetting
                    .Builder<>("maxSpectators", 8, "max-spectators", Integer.class)
                    .withFilter(value -> value >= 0)
                    .build();
        }
    },

    MAX_GAME_DURATION {
        @Override
        public Setting<?> getSetting() {
            return new SimpleConfigSetting
                    .Builder<>("maxGameDuration", 10800, "max-game-duration", Integer.class)
                    .withFilter(value -> value > 0)
                    .build();
        }
    },

    STARTING_CYCLE_DURATION {
        @Override
        public Setting<?> getSetting() {
            return new SimpleConfigSetting
                    .Builder<>("startingCycleDuration", 10, "starting-cycle-duration", Integer.class)
                    .withFilter(value -> value >= 0)
                    .build();
        }
    },

    ALLOW_WAITING_CHAT {
        @Override
        public Setting<?> getSetting() {
            return new SimpleConfigSetting
                    .Builder<>("allowWaitingChat", true, "waiting-cycle-chat.enabled", Boolean.class)
                    .build();
        }
    },

    WAITING_CHAT_FORMAT {
        @Override
        public Setting<?> getSetting() {

            String defaultFormat = String.format("%s &r: %s", PlaceholderEnum.PLAYER_NAME.get(), PlaceholderEnum.MESSAGE.get());

            return new SimpleConfigSetting
                    .Builder<>("waitingChatFormat", defaultFormat, "waiting-cycle-chat.format", String.class)
                    .withFilter(value -> value != null && !value.isEmpty())
                    .build();
        }
    },

    ALLOW_GAME_CHAT {
        @Override
        public Setting<?> getSetting() {
            return new SimpleConfigSetting
                    .Builder<>("allowGameChat", true, "game-chat.enabled", Boolean.class)
                    .build();
        }
    },

    GAME_CHAT_FORMAT {
        @Override
        public Setting<?> getSetting() {

            String defaultFormat = String.format("%s &r: %s", PlaceholderEnum.PLAYER_NAME.get(), PlaceholderEnum.MESSAGE.get());

            return new SimpleConfigSetting
                    .Builder<>("gameChatFormat", defaultFormat, "game-chat.format", String.class)
                    .withFilter(value -> value != null && !value.isEmpty())
                    .build();
        }
    },

    ALLOW_SPECTATOR_CHAT {
        @Override
        public Setting<?> getSetting() {
            return new SimpleConfigSetting
                    .Builder<>("allowSpectatorChat", true, "spectator-chat.enabled", Boolean.class)
                    .build();
        }
    },

    SPECTATOR_CHAT_FORMAT {
        @Override
        public Setting<?> getSetting() {

            String defaultFormat = String.format("&7%s &r: %s", PlaceholderEnum.PLAYER_NAME.get(), PlaceholderEnum.MESSAGE.get());

            return new SimpleConfigSetting
                    .Builder<>("spectatorChatFormat", defaultFormat, "spectator-chat.format", String.class)
                    .withFilter(value -> value != null && !value.isEmpty())
                    .build();
        }
    },

    GAME_SPAWN {
        @Override
        public Setting<?> getSetting() {
            return new LocationSetting
                    .Builder("gameSpawn", new Location(Bukkit.getWorld("world"), 0, 60, 0), "game-spawn")
                    .withFilter(location -> location.getWorld() != null)
                    .build();
        }
    },

    ALLOW_AUTO_START {
        @Override
        public Setting<?> getSetting() {
            return new SimpleConfigSetting
                    .Builder<>("allowAutoStart", true, "auto-start", Boolean.class)
                    .build();
        }
    };

    public abstract Setting<?> getSetting();
}
