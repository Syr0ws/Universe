package com.github.syr0ws.universe.modules;

import com.github.syr0ws.universe.Game;
import com.github.syr0ws.universe.modules.border.impl.CraftBorderModule;
import com.github.syr0ws.universe.modules.chat.impl.CraftChatModule;
import com.github.syr0ws.universe.modules.combat.impl.DefaultCombatModule;
import com.github.syr0ws.universe.modules.scoreboard.impl.CraftScoreboardModule;

public enum ModuleEnum {

    COMBAT_MODULE("CombatModule") {

        @Override
        public Module newInstance(Game game) {
            return new DefaultCombatModule(game);
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

    SCOREBOARD_MODULE("ScoreboardModule") {

        @Override
        public Module newInstance(Game game) {
            return new CraftScoreboardModule(game);
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
