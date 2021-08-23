package com.github.syr0ws.universe.sdk.placeholders;

public enum PlaceholderEnum implements Placeholder {

    PLAYER_NAME("player"), DISPLAY_NAME("display_name"), MESSAGE("message");

    private final String placeholder;

    PlaceholderEnum(String placeholder) {
        this.placeholder = placeholder;
    }

    @Override
    public String get() {
        return "%" + this.placeholder + "%";
    }
}
