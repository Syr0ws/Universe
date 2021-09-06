package com.github.syr0ws.universe.sdk.modules.chat.impl;

import com.github.syr0ws.universe.sdk.Game;
import com.github.syr0ws.universe.sdk.listeners.ListenerManager;
import com.github.syr0ws.universe.sdk.modules.GameModule;
import com.github.syr0ws.universe.sdk.modules.ModuleEnum;
import com.github.syr0ws.universe.sdk.modules.chat.ChatListener;
import com.github.syr0ws.universe.sdk.modules.chat.ChatModel;
import com.github.syr0ws.universe.sdk.modules.chat.ChatModule;

public class CraftChatModule extends GameModule implements ChatModule {

    private final ChatModel model;

    public static final String MODULE_NAME = "ChatModule";

    public CraftChatModule(Game game) {
        super(game);
        this.model = new CraftChatModel();
    }

    @Override
    public void enable() {
        super.enable();

        ListenerManager manager = super.getListenerManager();
        manager.addListener(new ChatListener(this.model));
    }

    @Override
    public String getName() {
        return MODULE_NAME;
    }

    @Override
    public ChatModel getChatModel() {
        return this.model;
    }
}
