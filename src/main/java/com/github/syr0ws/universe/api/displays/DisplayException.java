package com.github.syr0ws.universe.api.displays;

public class DisplayException extends Exception {

    public DisplayException(String message) {
        super(message);
    }

    public DisplayException(String message, Throwable cause) {
        super(message, cause);
    }
}
