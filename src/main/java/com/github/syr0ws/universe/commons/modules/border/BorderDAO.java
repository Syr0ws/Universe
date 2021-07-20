package com.github.syr0ws.universe.commons.modules.border;

import java.util.Collection;

public interface BorderDAO {

    Border loadBorder(String name) throws BorderException;

    Collection<? extends Border> loadBorders() throws BorderException;
}
