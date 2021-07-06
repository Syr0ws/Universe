package com.github.syr0ws.universe.modules;

import com.github.syr0ws.universe.Game;
import com.github.syr0ws.universe.modules.chat.impl.DefaultChatModule;
import com.github.syr0ws.universe.modules.combat.impl.DefaultCombatModule;

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
            return new DefaultChatModule(game);
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
