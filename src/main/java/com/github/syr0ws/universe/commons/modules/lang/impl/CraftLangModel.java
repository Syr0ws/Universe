package com.github.syr0ws.universe.commons.modules.lang.impl;

import com.github.syr0ws.universe.commons.modules.lang.LangModel;
import com.github.syr0ws.universe.commons.modules.lang.messages.Message;

import java.util.*;

public class CraftLangModel implements LangModel {

    private Locale locale;
    private final Map<String, Message> messages = new HashMap<>();

    public CraftLangModel() {
        this.locale = DEFAULT_LOCALE;
    }

    public CraftLangModel(Locale locale) {
        this.setCurrentLocale(locale, new HashMap<>());
    }

    @Override
    public void addMessage(String key, Message message) {

        if(key == null)
            throw new IllegalArgumentException("Key cannot be null.");

        if(message == null)
            throw new IllegalArgumentException("Message cannot be null.");

        key = this.getKey(key);

        if(this.isSet(key))
            throw new IllegalArgumentException(String.format("Key '%s' already exists. Remove it first.", key));

        this.messages.put(key, message);
    }

    @Override
    public void removeMessage(String key) {

        if(key == null)
            throw new IllegalArgumentException("Key cannot be null.");

        this.messages.remove(this.getKey(key));
    }

    @Override
    public void setCurrentLocale(Locale locale, Map<String, Message> messages) {
        this.locale = locale == null ? DEFAULT_LOCALE : locale;
        this.messages.clear();
        this.messages.putAll(messages);
    }

    @Override
    public boolean isSet(String key) {
        return this.messages.containsKey(this.getKey(key));
    }

    @Override
    public Locale getCurrentLocale() {
        return this.locale;
    }

    @Override
    public Locale getDefaultLocale() {
        return DEFAULT_LOCALE;
    }

    @Override
    public Optional<Message> getMessage(String key) {
        key = this.getKey(key);
        return Optional.ofNullable(this.messages.get(key));
    }

    @Override
    public Map<String, Message> getMessages() {
        return Collections.unmodifiableMap(this.messages);
    }

    private String getKey(String key) {
        return key.toLowerCase();
    }
}
