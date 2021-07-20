package com.github.syr0ws.universe.sdk.settings;

public class SettingValidationException extends RuntimeException {

    public SettingValidationException(String message) {
        super(message);
    }

    public SettingValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
