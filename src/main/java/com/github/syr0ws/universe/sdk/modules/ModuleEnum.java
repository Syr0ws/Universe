package com.github.syr0ws.universe.sdk.modules;

import com.github.syr0ws.universe.api.modules.Module;
import com.github.syr0ws.universe.sdk.modules.border.impl.CraftBorderModule;
import com.github.syr0ws.universe.sdk.modules.chat.impl.CraftChatModule;
import com.github.syr0ws.universe.sdk.modules.combat.impl.CraftCombatModule;
import com.github.syr0ws.universe.sdk.modules.lang.impl.CraftLangModule;
import com.github.syr0ws.universe.sdk.modules.view.impl.CraftViewModule;
import com.github.syr0ws.universe.sdk.modules.weather.impl.CraftWeatherModule;
import com.github.syr0ws.universe.sdk.Game;

public enum ModuleEnum {

    COMBAT_MODULE("CombatModule") {

        @Override
        public Module newInstance(Game game) {
            return new CraftCombatModule(game);
        }
    },

    CHAT_MODULE("ChatModule") {

        @Override
        public Module newInstance(Game game) {
            return new CraftChatModule(game);
        }
    },

    BORDER_MODULE("BorderModule") {

        @Override
        public Module newInstance(Game game) {
            return new CraftBorderModule(game);
        }
    },

    VIEW_MODULE("ViewModule") {

        @Override
        public Module newInstance(Game game) {
            return new CraftViewModule(game);
        }
    },

    LANG_MODULE("LangModule") {

        @Override
        public Module newInstance(Game game) {
            return new CraftLangModule(game);
        }
    },

    WEATHER_MODULE("WeatherModule") {

        @Override
        public Module newInstance(Game game) {
            return new CraftWeatherModule(game);
        }
    };

    private final String name;

    ModuleEnum(String name) {
        this.name = name;
    }

    public abstract Module newInstance(Game game);

    public String getName() {
        return this.name;
    }
}
