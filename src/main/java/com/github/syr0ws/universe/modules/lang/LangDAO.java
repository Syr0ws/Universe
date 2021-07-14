package com.github.syr0ws.universe.modules.lang;

import com.github.syr0ws.universe.modules.lang.messages.Message;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Collection;
import java.util.Locale;
import java.util.Map;

public interface LangDAO {

    Map<String, Message> loadMessages(Locale locale) throws LangException;
}
