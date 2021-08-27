package com.github.syr0ws.universe.sdk.chat;

import com.github.syr0ws.universe.api.settings.MutableSetting;
import com.github.syr0ws.universe.sdk.modules.chat.Chat;
import com.github.syr0ws.universe.sdk.modules.chat.ChatMessage;
import com.github.syr0ws.universe.sdk.modules.chat.ChatPriority;
import com.github.syr0ws.universe.sdk.placeholders.PlaceholderEnum;
import com.github.syr0ws.universe.api.game.settings.GameSettings;
import com.github.syr0ws.universe.sdk.displays.types.Message;
import com.github.syr0ws.universe.api.game.model.GameModel;

public class DefaultWaitingChat implements Chat {

    private final GameModel model;

    public DefaultWaitingChat(GameModel model) {

        if(model == null)
            throw new IllegalArgumentException("GameModel cannot be null.");

        this.model = model;
    }

    @Override
    public void onChat(ChatMessage message) {

        GameSettings settings = this.model.getSettings();
        String format = settings.getWaitingChatFormatSetting().getValue();

        // Creating the message.
        Message msg = new Message(format);
        msg.addPlaceholder(PlaceholderEnum.PLAYER_NAME.get(), message.getSender().getName());
        msg.addPlaceholder(PlaceholderEnum.MESSAGE.get(), message.getMessage());

        message.setFormat(msg.getText());
    }

    @Override
    public boolean canSend(ChatMessage message) {
        return this.isChatAllowed() && !this.model.isStarted();
    }

    @Override
    public ChatPriority getPriority() {
        return ChatPriority.NORMAL;
    }

    public GameModel getModel() {
        return this.model;
    }

    private boolean isChatAllowed() {
        MutableSetting<Boolean> setting = this.model.getSettings().getAllowWaitingChatSetting();
        return setting.getValue();
    }
}
