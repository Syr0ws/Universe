package com.github.syr0ws.universe.sdk.game.cycle;

public class GameCycleException extends RuntimeException {

    public GameCycleException(String message) {
        super(message);
    }

    public GameCycleException(String message, Throwable cause) {
        super(message, cause);
    }
}
