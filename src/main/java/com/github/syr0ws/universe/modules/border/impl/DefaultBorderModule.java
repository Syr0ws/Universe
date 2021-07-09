package com.github.syr0ws.universe.modules.border.impl;

import com.github.syr0ws.universe.Game;
import com.github.syr0ws.universe.modules.GameModule;
import com.github.syr0ws.universe.modules.ModuleEnum;
import com.github.syr0ws.universe.modules.ModuleException;
import com.github.syr0ws.universe.modules.border.BorderLoader;
import com.github.syr0ws.universe.modules.border.BorderModule;

public class DefaultBorderModule extends GameModule implements BorderModule {

    public DefaultBorderModule(Game game) {
        super(game);
    }

    @Override
    public void enable() throws ModuleException {

        BorderLoader loader = new ConfigBorderLoader(this.getConfig());
        loader.loadBorders();
    }

    @Override
    public void disable() throws ModuleException {

    }

    @Override
    public String getName() {
        return ModuleEnum.BORDER_MODULE.getName();
    }
}
