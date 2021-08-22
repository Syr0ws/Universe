package com.github.syr0ws.universe.commons.modules.chat.impl;

import com.github.syr0ws.universe.sdk.Game;
import com.github.syr0ws.universe.sdk.listeners.ListenerManager;
import com.github.syr0ws.universe.commons.modules.GameModule;
import com.github.syr0ws.universe.commons.modules.ModuleEnum;
import com.github.syr0ws.universe.commons.modules.ModuleException;
import com.github.syr0ws.universe.commons.modules.chat.ChatListener;
import com.github.syr0ws.universe.commons.modules.chat.ChatModel;
import com.github.syr0ws.universe.commons.modules.chat.ChatModule;

public class CraftChatModule extends GameModule implements ChatModule {

    private final ChatModel model;

    public CraftChatModule(Game game) {
        super(game);
        this.model = new CraftChatModel();
    }

    @Override
    public void load() {

    }

    @Override
    public void enable() {

        ListenerManager manager = super.getListenerManager();
        manager.addListener(new ChatListener(this.model));
    }

    @Override
    public void disable() {
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
