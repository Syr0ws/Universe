package com.github.syr0ws.universe.modules.lang.messages.impl;

import com.github.syr0ws.universe.modules.lang.messages.Message;
import com.github.syr0ws.universe.placeholders.PlaceholderContainer;

public class Text extends PlaceholderContainer implements Message {

    private final String text;

    public Text(String text) {

        if(text == null)
            throw new IllegalArgumentException("Text cannot be null.");

        this.text = text;
    }

    public String getText() {
        return super.format(this.text);
    }
}
