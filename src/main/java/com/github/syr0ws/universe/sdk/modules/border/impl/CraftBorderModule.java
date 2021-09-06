package com.github.syr0ws.universe.sdk.modules.border.impl;

import com.github.syr0ws.universe.sdk.Game;
import com.github.syr0ws.universe.sdk.modules.GameModule;
import com.github.syr0ws.universe.sdk.modules.ModuleEnum;
import com.github.syr0ws.universe.sdk.modules.border.BorderDAO;
import com.github.syr0ws.universe.sdk.modules.border.BorderModel;
import com.github.syr0ws.universe.sdk.modules.border.BorderModule;
import com.github.syr0ws.universe.sdk.modules.border.BorderService;

public class CraftBorderModule extends GameModule implements BorderModule {

    private BorderModel model;
    private BorderService service;

    public static final String MODULE_NAME = "BorderModule";

    public CraftBorderModule(Game game) {
        super(game);
    }

    @Override
    public void load() {
        super.load();
        super.loadConfig();
    }

    @Override
    public void enable() {
        super.enable();

        this.model = new CraftBorderModel();

        BorderDAO dao = new ConfigBorderDAO(this.getConfig());

        this.service = new CraftBorderService(this.model, dao);
        this.service.loadBorders();
    }

    @Override
    public String getName() {
        return MODULE_NAME;
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
