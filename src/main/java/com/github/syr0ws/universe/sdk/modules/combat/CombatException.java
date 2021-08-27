package com.github.syr0ws.universe.sdk.modules.combat;

public class CombatException extends RuntimeException {

    public CombatException(String message) {
        super(message);
    }

    public CombatException(String message, Throwable cause) {
        super(message, cause);
    }
}
