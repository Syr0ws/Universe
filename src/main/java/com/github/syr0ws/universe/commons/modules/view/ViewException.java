package com.github.syr0ws.universe.commons.modules.view;

public class ViewException extends RuntimeException {

    public ViewException(String message) {
        super(message);
    }

    public ViewException(String message, Throwable cause) {
        super(message, cause);
    }
}
