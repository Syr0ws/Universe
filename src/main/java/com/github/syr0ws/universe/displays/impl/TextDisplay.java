package com.github.syr0ws.universe.displays.impl;

import com.github.syr0ws.universe.displays.Display;
import com.github.syr0ws.universe.modules.lang.LangService;
import com.github.syr0ws.universe.modules.lang.messages.impl.Text;
import com.github.syr0ws.universe.placeholders.PlaceholderContainer;

public abstract class TextDisplay extends PlaceholderContainer implements Display {

    private static final String LANG_KEY_START = "${";
    private static final String LANG_KEY_END = "}";

    private final LangService service;

    public TextDisplay() {
        this.service = null;
    }

    public TextDisplay(LangService service) {
        this.service = service;
    }

    @Override
    protected String format(String text) {
        text = this.parseLang(text);
        return super.format(text);
    }

    private String parseLang(String text) {

        if(this.service == null) return text;

        int start = text.indexOf(LANG_KEY_START);
        int end = text.indexOf(LANG_KEY_END);

        // Parsing all the lang key found.
        while(start != -1 && end != -1) {

            String key = text.substring(start + 2, end);

            String message = "";

            // Retrieving the text associated to the key and setting it into
            // the message variable if it exists.
            try { message = this.service.getMessage(key, Text.class).getText();
            } catch (IllegalArgumentException e) { e.printStackTrace(); }

            // Replacing the key by the message found.
            text = text.replace(String.format("%s%s%s", LANG_KEY_START, key, LANG_KEY_END), message);

            start = text.indexOf(LANG_KEY_START);
            end = text.indexOf(LANG_KEY_END);
        }
        return text;
    }
}
