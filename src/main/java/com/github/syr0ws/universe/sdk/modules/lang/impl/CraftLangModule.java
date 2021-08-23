package com.github.syr0ws.universe.sdk.modules.lang.impl;

import com.github.syr0ws.universe.sdk.Game;
import com.github.syr0ws.universe.sdk.modules.GameModule;
import com.github.syr0ws.universe.sdk.modules.ModuleEnum;
import com.github.syr0ws.universe.sdk.modules.lang.LangDAO;
import com.github.syr0ws.universe.sdk.modules.lang.LangModel;
import com.github.syr0ws.universe.sdk.modules.lang.LangModule;
import com.github.syr0ws.universe.sdk.modules.lang.LangService;
import com.github.syr0ws.universe.sdk.modules.lang.config.ConfigLangDAO;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Locale;

public class CraftLangModule extends GameModule implements LangModule {

    private final LangModel model;
    private final LangService service;

    public CraftLangModule(Game game) {
        super(game);

        LangDAO dao = new ConfigLangDAO(this.getGame());

        this.model = new CraftLangModel();
        this.service = new CraftLangService(this.model, dao);
    }

    @Override
    public void load() {
    }

    @Override
    public void enable() {

        FileConfiguration config = this.getConfig();

        String lang = config.getString("lang", LangModel.DEFAULT_LOCALE.getLanguage());
        Locale locale = Locale.forLanguageTag(lang);

        this.service.loadMessages(locale);
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
        return this.model;
    }

    @Override
    public LangService getLangService() {
        return this.service;
    }
}
