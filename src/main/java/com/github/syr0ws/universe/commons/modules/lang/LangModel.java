package com.github.syr0ws.universe.commons.modules.lang;

import com.github.syr0ws.universe.commons.modules.lang.messages.Message;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public interface LangModel {

    Locale DEFAULT_LOCALE = Locale.ENGLISH;

    void addMessage(String key, Message message);

    void removeMessage(String key);

    void setCurrentLocale(Locale locale, Map<String, Message> messages);

    boolean isSet(String key);

    Locale getCurrentLocale();

    Locale getDefaultLocale();

    Optional<Message> getMessage(String key);

    Map<String, Message> getMessages();
}
