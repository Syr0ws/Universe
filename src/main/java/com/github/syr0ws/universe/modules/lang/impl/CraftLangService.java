package com.github.syr0ws.universe.modules.lang.impl;

import com.github.syr0ws.universe.modules.lang.LangDAO;
import com.github.syr0ws.universe.modules.lang.LangException;
import com.github.syr0ws.universe.modules.lang.LangModel;
import com.github.syr0ws.universe.modules.lang.LangService;
import com.github.syr0ws.universe.modules.lang.messages.Message;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public class CraftLangService implements LangService {

    private final LangModel model;
    private final LangDAO dao;

    public CraftLangService(LangModel model, LangDAO dao) {

        if(model == null)
            throw new IllegalArgumentException("LangModel cannot be null.");

        if(dao == null)
            throw new IllegalArgumentException("LangDAO cannot be null.");

        this.model = model;
        this.dao = dao;
    }

    @Override
    public void loadMessages(Locale locale) {

        try {

            Map<String, Message> messages = this.dao.loadMessages(locale);
            this.model.setCurrentLocale(locale, messages);

        } catch (LangException e) { e.printStackTrace(); }
    }

    @Override
    public void reloadMessages() {

        Locale locale = this.model.getCurrentLocale();
        this.loadMessages(locale);
    }

    @Override
    public Message getMessage(String key) {

        Optional<Message> optional = this.model.getMessage(key);

        return optional.orElseThrow(() -> new IllegalArgumentException(String.format("No message found with key '%s'.", key)));
    }

    @Override
    public <T extends Message> T getMessage(String key, Class<T> clazz) {

        Message message = this.getMessage(key);

        if(!clazz.isInstance(message))
            throw new IllegalArgumentException(String.format("%s is not an instance of %s.", message.getClass().getName(), clazz.getName()));

        return clazz.cast(message);
    }
}
