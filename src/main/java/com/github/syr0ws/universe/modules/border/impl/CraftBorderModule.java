package com.github.syr0ws.universe.modules.border.impl;

import com.github.syr0ws.universe.Game;
import com.github.syr0ws.universe.modules.GameModule;
import com.github.syr0ws.universe.modules.ModuleEnum;
import com.github.syr0ws.universe.modules.ModuleException;
import com.github.syr0ws.universe.modules.border.BorderDAO;
import com.github.syr0ws.universe.modules.border.BorderModel;
import com.github.syr0ws.universe.modules.border.BorderModule;
import com.github.syr0ws.universe.modules.border.BorderService;

public class CraftBorderModule extends GameModule implements BorderModule {

    private BorderModel model;
    private BorderService service;

    public CraftBorderModule(Game game) {
        super(game);
    }

    @Override
    public void enable() throws ModuleException {

        this.model = new CraftBorderModel();

        BorderDAO dao = new ConfigBorderDAO(this.getConfig());

        this.service = new CraftBorderService(this.model, dao);
        this.service.loadBorders();
    }

    @Override
    public void disable() throws ModuleException {

    }

    @Override
    public String getName() {
        return ModuleEnum.BORDER_MODULE.getName();
    }

    @Override
    public BorderModel getBorderModel() {
        return this.model;
    }

    @Override
    public BorderService getBorderService() {
        return this.service;
    }
}
