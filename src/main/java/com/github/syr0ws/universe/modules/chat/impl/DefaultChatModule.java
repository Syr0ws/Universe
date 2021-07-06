package com.github.syr0ws.universe.modules.chat.impl;

import com.github.syr0ws.universe.Game;
import com.github.syr0ws.universe.listeners.ListenerManager;
import com.github.syr0ws.universe.modules.GameModule;
import com.github.syr0ws.universe.modules.ModuleEnum;
import com.github.syr0ws.universe.modules.ModuleException;
import com.github.syr0ws.universe.modules.chat.ChatListener;
import com.github.syr0ws.universe.modules.chat.ChatModule;
import com.github.syr0ws.universe.modules.chat.ChatService;

public class DefaultChatModule extends GameModule implements ChatModule {

    private final ChatService service;

    public DefaultChatModule(Game game) {
        super(game);
        this.service = new DefaultChatService();
    }

    @Override
    public void enable() throws ModuleException {

        ListenerManager manager = super.getListenerManager();
        manager.addListener(new ChatListener(this.service));
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
    public ChatService getChatService() {
        return this.service;
    }
}
