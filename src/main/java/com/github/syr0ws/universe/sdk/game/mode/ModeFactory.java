package com.github.syr0ws.universe.sdk.game.mode;

import java.util.HashMap;
import java.util.Map;

public class ModeFactory {

    private static final Map<ModeType, Mode> modes = new HashMap<>();

    public static Mode getMode(ModeType type) {

        if(!modes.containsKey(type))
            throw new IllegalArgumentException("No mode found.");

        return modes.get(type);
    }

    public static void registerMode(Mode mode) {

        if(mode == null)
            throw new IllegalArgumentException("Mode cannot be null.");

        if(mode.getType() == null)
            throw new IllegalArgumentException("ModeType cannot be null.");

        modes.put(mode.getType(), mode);
    }
}
