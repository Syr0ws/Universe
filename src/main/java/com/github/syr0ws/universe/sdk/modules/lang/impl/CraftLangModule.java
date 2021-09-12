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

    public static final String MODULE_NAME = "LangModule";

    public CraftLangModule(Game game) {
        super(game);

        LangDAO dao = new ConfigLangDAO(this.getGame());

        this.model = new CraftLangModel();
        this.service = new CraftLangService(this.model, dao);
    }

    @Override
    public void enable() {
        super.enable();

        FileConfiguration config = this.getConfig();

        String lang = config.getString("lang", LangModel.DEFAULT_LOCALE.getLanguage());
        Locale locale = Locale.forLanguageTag(lang);

        this.service.loadMessages(locale);
    }

    @Override
    public String getName() {
        return MODULE_NAME;
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
