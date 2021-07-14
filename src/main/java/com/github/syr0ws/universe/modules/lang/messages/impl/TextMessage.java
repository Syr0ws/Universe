package com.github.syr0ws.universe.modules.lang.messages.impl;

public class TextMessage extends Text {

    private final String text;

    public TextMessage(String text) {

        if(text == null)
            throw new IllegalArgumentException("TextMessage cannot be null.");

        this.text = text;
    }

    public String getText() {
        return super.format(this.text);
    }
}
