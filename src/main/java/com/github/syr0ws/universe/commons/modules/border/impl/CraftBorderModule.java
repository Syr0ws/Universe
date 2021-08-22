package com.github.syr0ws.universe.commons.modules.border.impl;

import com.github.syr0ws.universe.sdk.Game;
import com.github.syr0ws.universe.commons.modules.GameModule;
import com.github.syr0ws.universe.commons.modules.ModuleEnum;
import com.github.syr0ws.universe.commons.modules.ModuleException;
import com.github.syr0ws.universe.commons.modules.border.BorderDAO;
import com.github.syr0ws.universe.commons.modules.border.BorderModel;
import com.github.syr0ws.universe.commons.modules.border.BorderModule;
import com.github.syr0ws.universe.commons.modules.border.BorderService;

public class CraftBorderModule extends GameModule implements BorderModule {

    private BorderModel model;
    private BorderService service;

    public CraftBorderModule(Game game) {
        super(game);
    }

    @Override
    public void load() {
        super.loadConfig();
    }

    @Override
    public void enable() {

        this.model = new CraftBorderModel();

        BorderDAO dao = new ConfigBorderDAO(this.getConfig());

        this.service = new CraftBorderService(this.model, dao);
        this.service.loadBorders();
    }

    @Override
    public void disable() {

    }

    @Override
    public String getName() {
        return ModuleEnum.BORDER_MODULE.getName();
    }

    @Override
    public String getConfigName() {
        return "border-module.yml";
    }

    @Override
    public boolean useDefaultConfig() {
        return true;
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
