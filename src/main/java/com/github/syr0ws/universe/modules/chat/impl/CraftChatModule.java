package com.github.syr0ws.universe.modules.chat.impl;

import com.github.syr0ws.universe.Game;
import com.github.syr0ws.universe.listeners.ListenerManager;
import com.github.syr0ws.universe.modules.GameModule;
import com.github.syr0ws.universe.modules.ModuleEnum;
import com.github.syr0ws.universe.modules.ModuleException;
import com.github.syr0ws.universe.modules.chat.ChatListener;
import com.github.syr0ws.universe.modules.chat.ChatModel;
import com.github.syr0ws.universe.modules.chat.ChatModule;

public class CraftChatModule extends GameModule implements ChatModule {

    private final ChatModel model;

    public CraftChatModule(Game game) {
        super(game);
        this.model = new CraftChatModel();
    }

    @Override
    public void enable() throws ModuleException {

        ListenerManager manager = super.getListenerManager();
        manager.addListener(new ChatListener(this.model));
    }

    @Override
    public void disable() throws ModuleException {

        super.getListenerManager().removeListeners();
    }

    @Override
    public String getName() {
        return ModuleEnum.CHAT_MODULE.getName();
    }

    @Override
    public ChatModel getChatModel() {
        return this.model;
    }
}
