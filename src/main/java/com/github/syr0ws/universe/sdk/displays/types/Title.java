package com.github.syr0ws.universe.sdk.displays.types;

import com.github.syr0ws.universe.sdk.modules.lang.LangService;

public abstract class Title extends TextDisplay {

    public static final int DEFAULT_FADE_IN = 10;
    public static final int DEFAULT_STAY = 70;
    public static final int DEFAULT_FADE_OUT = 20;

    private final String title, subtitle;
    private final int fadeIn, fadeOut, stay;

    public Title(String title, String subtitle) {
        this(null, title, subtitle);
    }

    public Title(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        this(null, title, subtitle, fadeIn, stay, fadeOut);
    }

    public Title(LangService service, String title, String subtitle) {
        this(service, title, subtitle, DEFAULT_FADE_IN, DEFAULT_STAY, DEFAULT_FADE_OUT);
    }

    public Title(LangService service, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        super(service);

        if(title == null)
            throw new IllegalArgumentException("Title cannot be null.");

        if(subtitle == null)
            throw new IllegalArgumentException("Subtitle cannot be null.");

        this.title = title;
        this.subtitle = subtitle;
        this.fadeIn = fadeIn;
        this.stay = stay;
        this.fadeOut = fadeOut;
    }

    public String getTitle() {
        return super.format(this.title);
    }

    public String getSubtitle() {
        return super.format(this.subtitle);
    }

    public int getFadeIn() {
        return this.fadeIn;
    }

    public int getFadeOut() {
        return this.fadeOut;
    }

    public int getStay() {
        return this.stay;
    }
}
