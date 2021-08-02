package com.github.syr0ws.universe.commons.chat;

import com.github.syr0ws.universe.commons.modules.chat.Chat;
import com.github.syr0ws.universe.commons.modules.chat.ChatMessage;
import com.github.syr0ws.universe.commons.modules.chat.ChatPriority;
import com.github.syr0ws.universe.commons.placeholders.PlaceholderEnum;
import com.github.syr0ws.universe.commons.settings.GameSettings;
import com.github.syr0ws.universe.sdk.displays.types.Message;
import com.github.syr0ws.universe.sdk.game.model.GameModel;
import com.github.syr0ws.universe.sdk.settings.types.MutableSetting;

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
