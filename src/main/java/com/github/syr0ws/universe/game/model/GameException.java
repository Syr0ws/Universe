package com.github.syr0ws.universe.game.model;

public class GameException extends Exception {

    public GameException(String message) {
        super(message);
    }

    public GameException(String message, Throwable cause) {
        super(message, cause);
    }
}
