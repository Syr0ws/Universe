package com.github.syr0ws.universe.modules.lang;

import com.github.syr0ws.universe.modules.lang.messages.Message;

import java.util.Locale;

public interface LangService {

    void loadMessages(Locale locale);

    void reloadMessages();

    Message getMessage(String key);

    <T extends Message> T getMessage(String key, Class<T> clazz);
}
