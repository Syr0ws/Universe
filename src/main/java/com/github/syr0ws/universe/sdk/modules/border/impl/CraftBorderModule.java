package com.github.syr0ws.universe.sdk.modules.border.impl;

import com.github.syr0ws.universe.api.GamePlugin;
import com.github.syr0ws.universe.sdk.Game;
import com.github.syr0ws.universe.sdk.modules.GameModule;
import com.github.syr0ws.universe.sdk.modules.ModuleEnum;
import com.github.syr0ws.universe.sdk.modules.border.BorderDAO;
import com.github.syr0ws.universe.sdk.modules.border.BorderModel;
import com.github.syr0ws.universe.sdk.modules.border.BorderModule;
import com.github.syr0ws.universe.sdk.modules.border.BorderService;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;

public class CraftBorderModule extends GameModule implements BorderModule {

    public CraftBorderModule(Game game) {
        super(game);
    }

    @Override
    public void load() {
        super.loadConfig();
    }

    @Override
    public void enable() {

        this.bindBorderModel();
        this.bindBorderService();
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
        ServicesManager manager = super.getGame().getServicesManager();
        return manager.load(BorderModel.class);
    }

    @Override
    public BorderService getBorderService() {
        ServicesManager manager = super.getGame().getServicesManager();
        return manager.load(BorderService.class);
    }

    private void bindBorderModel() {

        GamePlugin plugin = super.getGame();
        BorderModel model = new CraftBorderModel();

        ServicesManager manager = plugin.getServicesManager();
        manager.register(BorderModel.class, model, plugin, ServicePriority.Normal);
    }

    private void bindBorderService() {

        GamePlugin plugin = super.getGame();

        BorderDAO dao = new ConfigBorderDAO(this.getConfig());
        BorderModel model = this.getBorderModel();
        BorderService service = new CraftBorderService(model, dao);

        ServicesManager manager = plugin.getServicesManager();
        manager.register(BorderService.class, service, plugin, ServicePriority.Normal);
    }
}
