package com.github.syr0ws.universe.sdk.modules.lang.impl;

import com.github.syr0ws.universe.api.GamePlugin;
import com.github.syr0ws.universe.sdk.Game;
import com.github.syr0ws.universe.sdk.modules.GameModule;
import com.github.syr0ws.universe.sdk.modules.ModuleEnum;
import com.github.syr0ws.universe.sdk.modules.lang.LangDAO;
import com.github.syr0ws.universe.sdk.modules.lang.LangModel;
import com.github.syr0ws.universe.sdk.modules.lang.LangModule;
import com.github.syr0ws.universe.sdk.modules.lang.LangService;
import com.github.syr0ws.universe.sdk.modules.lang.config.ConfigLangDAO;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;

import java.util.Locale;

public class CraftLangModule extends GameModule implements LangModule {

    public CraftLangModule(Game game) {
        super(game);
    }

    @Override
    public void load() {

    }

    @Override
    public void enable() {

        this.bindLangModel();
        this.bindLangService();

        this.loadLang();
    }

    @Override
    public void disable() {

    }

    @Override
    public String getName() {
        return ModuleEnum.LANG_MODULE.getName();
    }

    @Override
    public boolean useDefaultConfig() {
        return true;
    }

    @Override
    public LangModel getLangModel() {
        ServicesManager manager = super.getGame().getServicesManager();
        return manager.load(LangModel.class);
    }

    @Override
    public LangService getLangService() {
        ServicesManager manager = super.getGame().getServicesManager();
        return manager.load(LangService.class);
    }

    private void bindLangModel() {

        GamePlugin plugin = super.getGame();
        LangModel model = new CraftLangModel();

        ServicesManager manager = plugin.getServicesManager();
        manager.register(LangModel.class, model, plugin, ServicePriority.Normal);
    }

    private void bindLangService() {

        Game plugin = super.getGame();

        LangDAO dao = new ConfigLangDAO(plugin);
        LangModel model = this.getLangModel();
        LangService service = new CraftLangService(model, dao);

        ServicesManager manager = plugin.getServicesManager();
        manager.register(LangService.class, service, plugin, ServicePriority.Normal);
    }

    private void loadLang() {

        FileConfiguration config = this.getConfig();

        String lang = config.getString("lang", LangModel.DEFAULT_LOCALE.getLanguage());
        Locale locale = Locale.forLanguageTag(lang);

        LangService service = this.getLangService();
        service.loadMessages(locale);
    }
}
