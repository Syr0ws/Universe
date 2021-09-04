package com.github.syr0ws.universe.sdk.modules.chat.impl;

import com.github.syr0ws.universe.api.GamePlugin;
import com.github.syr0ws.universe.api.services.GameServicePriority;
import com.github.syr0ws.universe.api.services.GameServicesManager;
import com.github.syr0ws.universe.sdk.Game;
import com.github.syr0ws.universe.sdk.listeners.ListenerManager;
import com.github.syr0ws.universe.sdk.modules.GameModule;
import com.github.syr0ws.universe.sdk.modules.ModuleEnum;
import com.github.syr0ws.universe.sdk.modules.chat.ChatListener;
import com.github.syr0ws.universe.sdk.modules.chat.ChatModel;
import com.github.syr0ws.universe.sdk.modules.chat.ChatModule;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;

public class CraftChatModule extends GameModule implements ChatModule {

    public CraftChatModule(Game game) {
        super(game);
    }

    @Override
    public void load() {

    }

    @Override
    public void enable() {

        // Binding classes.
        this.bindChatModel();

        // Registering listeners.
        this.registerListeners();
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
        GameServicesManager manager = super.getGame().getServicesManager();
        return manager.getProvider(ChatModel.class);
    }

    private void bindChatModel() {

        GamePlugin plugin = super.getGame();

        ChatModel model = new CraftChatModel();

        GameServicesManager manager = plugin.getServicesManager();
        manager.register(ChatModel.class, model, GameServicePriority.NORMAL);
    }

    private void registerListeners() {

        ChatModel model = this.getChatModel();

        ListenerManager manager = super.getListenerManager();
        manager.addListener(new ChatListener(model));
    }
}
