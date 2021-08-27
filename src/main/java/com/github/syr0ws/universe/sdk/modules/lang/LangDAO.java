package com.github.syr0ws.universe.sdk.modules.lang;

import com.github.syr0ws.universe.sdk.modules.lang.messages.Message;

import java.util.Locale;
import java.util.Map;

public interface LangDAO {

    Map<String, Message> loadMessages(Locale locale) throws LangException;
}
