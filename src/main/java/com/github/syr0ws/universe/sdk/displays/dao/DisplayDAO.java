package com.github.syr0ws.universe.sdk.displays.dao;

import com.github.syr0ws.universe.sdk.displays.Display;
import com.github.syr0ws.universe.sdk.displays.DisplayException;

import java.util.Collection;

public interface DisplayDAO {

    Display getDisplay(String path) throws DisplayException;

    Collection<Display> getDisplays(String path) throws DisplayException;
}
