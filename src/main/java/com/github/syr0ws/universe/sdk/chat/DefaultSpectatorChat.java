package com.github.syr0ws.universe.sdk.chat;

import com.github.syr0ws.universe.api.settings.MutableSetting;
import com.github.syr0ws.universe.sdk.game.mode.DefaultModeType;
import com.github.syr0ws.universe.sdk.modules.chat.Chat;
import com.github.syr0ws.universe.sdk.modules.chat.ChatMessage;
import com.github.syr0ws.universe.sdk.modules.chat.ChatPriority;
import com.github.syr0ws.universe.sdk.placeholders.PlaceholderEnum;
import com.github.syr0ws.universe.api.game.settings.GameSettings;
import com.github.syr0ws.universe.sdk.displays.types.Message;
import com.github.syr0ws.universe.api.game.mode.ModeType;
import com.github.syr0ws.universe.api.game.model.GameModel;
import com.github.syr0ws.universe.api.game.model.GamePlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultSpectatorChat implements Chat {

    private final GameModel model;

    public DefaultSpectatorChat(GameModel model) {

        if(model == null)
            throw new IllegalArgumentException("GameModel cannot be null.");

        this.model = model;
    }

    @Override
    public void onChat(ChatMessage message) {

        GameSettings settings = this.model.getSettings();
        String format = settings.getSpectatorChatFormatSetting().getValue();

        // Creating the message.
        Message msg = new Message(format);
        msg.addPlaceholder(PlaceholderEnum.PLAYER_NAME.get(), message.getSender().getName());
        msg.addPlaceholder(PlaceholderEnum.MESSAGE.get(), message.getMessage());

        // Retrieving receivers.
        List<Player> receivers = this.model.getOnlinePlayers().stream()
                .filter(gamePlayer -> gamePlayer.getModeType().equals(DefaultModeType.SPECTATOR))
                .map(GamePlayer::getPlayer)
                .collect(Collectors.toList());

        message.setFormat(msg.getText());
        message.setReceivers(receivers);
    }

    @Override
    public boolean canSend(ChatMessage message) {

        Player player = message.getSender();
        GamePlayer gamePlayer = this.model.getPlayer(player.getUniqueId());

        ModeType modeType = gamePlayer.getModeType();

        return this.isChatAllowed() && modeType.equals(DefaultModeType.SPECTATOR);
    }

    @Override
    public ChatPriority getPriority() {
        return ChatPriority.NORMAL;
    }

    public GameModel getModel() {
        return this.model;
    }

    private boolean isChatAllowed() {
        MutableSetting<Boolean> setting = this.model.getSettings().getAllowSpectatorChatSetting();
        return setting.getValue();
    }
}
