package com.github.syr0ws.universe.modules.combat;

public class CombatException extends RuntimeException {

    public CombatException(String message) {
        super(message);
    }

    public CombatException(String message, Throwable cause) {
        super(message, cause);
    }
}
