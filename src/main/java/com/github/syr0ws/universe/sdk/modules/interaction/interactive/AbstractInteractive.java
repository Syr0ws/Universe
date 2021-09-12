package com.github.syr0ws.universe.sdk.modules.interaction.interactive;

import com.github.syr0ws.universe.sdk.modules.interaction.Interactive;

public abstract class AbstractInteractive<T> implements Interactive<T> {

    private boolean interactive;

    @Override
    public void setInteractive(boolean interactive) {
        this.interactive = interactive;
    }

    @Override
    public boolean isInteractive() {
        return this.interactive;
    }
}
