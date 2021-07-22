package com.github.syr0ws.universe.sdk.timer.actions;

import com.github.syr0ws.universe.sdk.displays.Display;
import com.github.syr0ws.universe.sdk.timer.TimerAction;

public class DisplayAction implements TimerAction {

    private final Display display;

    public DisplayAction(Display display) {

        if(display == null)
            throw new IllegalArgumentException("Display cannot be null.");

        this.display = display;
    }

    @Override
    public void execute() {
        this.display.displayAll();
    }
}
