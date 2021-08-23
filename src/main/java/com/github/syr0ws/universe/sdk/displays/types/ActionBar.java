package com.github.syr0ws.universe.sdk.displays.types;

import com.github.syr0ws.universe.sdk.modules.lang.LangService;

public abstract class ActionBar extends TextDisplay {

    private final String text;

    public ActionBar(String text) {
        this(null, text);
    }

    public ActionBar(LangService service, String text) {
        super(service);

        if(text == null)
            throw new IllegalArgumentException("Text cannot be null.");

        this.text = text;
    }

    public String getText() {
        return super.format(this.text);
    }
}
