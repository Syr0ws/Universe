package com.github.syr0ws.universe.api.displays;

import com.github.syr0ws.universe.api.displays.Display;
import com.github.syr0ws.universe.api.displays.DisplayException;

import java.util.Collection;

public interface DisplayDAO {

    Display getDisplay(String path) throws DisplayException;

    Collection<Display> getDisplays(String path) throws DisplayException;
}
