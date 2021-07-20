package com.github.syr0ws.universe.sdk.game.model.mode;

public interface ModeManager {

    Mode getMode(ModeType type);

    void registerMode(Mode mode);

    void unregisterMode(ModeType type);
}
