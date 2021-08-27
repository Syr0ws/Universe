package com.github.syr0ws.universe.api.modules;

public class ModuleException extends Exception {

    public ModuleException(String message) {
        super(message);
    }

    public ModuleException(String message, Throwable cause) {
        super(message, cause);
    }
}
