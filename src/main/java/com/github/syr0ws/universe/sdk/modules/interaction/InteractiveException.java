package com.github.syr0ws.universe.sdk.modules.interaction;

public class InteractiveException extends Exception {

    public InteractiveException(String message) {
        super(message);
    }

    public InteractiveException(String message, Throwable cause) {
        super(message, cause);
    }
}
