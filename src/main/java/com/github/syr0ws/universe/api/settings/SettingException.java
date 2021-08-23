package com.github.syr0ws.universe.api.settings;

public class SettingException extends Exception {

    public SettingException(String message) {
        super(message);
    }

    public SettingException(String message, Throwable cause) {
        super(message, cause);
    }
}
