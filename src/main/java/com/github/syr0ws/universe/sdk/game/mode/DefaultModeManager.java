package com.github.syr0ws.universe.sdk.game.mode;

import com.github.syr0ws.universe.api.game.mode.Mode;
import com.github.syr0ws.universe.api.game.mode.ModeManager;
import com.github.syr0ws.universe.api.game.mode.ModeType;

import java.util.HashMap;
import java.util.Map;

public class DefaultModeManager implements ModeManager {

    private final Map<ModeType, Mode> modes = new HashMap<>();

    @Override
    public Mode getMode(ModeType type) {

        if(!this.modes.containsKey(type))
            throw new IllegalArgumentException("Mode not found.");

        return this.modes.get(type);
    }

    @Override
    public void registerMode(Mode mode) {

        if(mode == null)
            throw new IllegalArgumentException("Mode cannot be null.");

        if(this.modes.containsKey(mode.getType()))
            throw new IllegalArgumentException("Mode already registered.");

        this.modes.put(mode.getType(), mode);
    }

    @Override
    public void unregisterMode(ModeType type) {
        this.modes.remove(type);
    }
}
