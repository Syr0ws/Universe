package com.github.syr0ws.universe.modules.lang.impl;

import com.github.syr0ws.universe.Game;
import com.github.syr0ws.universe.modules.GameModule;
import com.github.syr0ws.universe.modules.ModuleEnum;
import com.github.syr0ws.universe.modules.ModuleException;
import com.github.syr0ws.universe.modules.lang.LangDAO;
import com.github.syr0ws.universe.modules.lang.LangModel;
import com.github.syr0ws.universe.modules.lang.LangModule;
import com.github.syr0ws.universe.modules.lang.LangService;
import com.github.syr0ws.universe.modules.lang.config.ConfigLangDAO;
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
    public void enable() throws ModuleException {

        FileConfiguration config = this.getConfig();

        String lang = config.getString("lang", LangModel.DEFAULT_LOCALE.getLanguage());
        Locale locale = Locale.forLanguageTag(lang);

        this.service.loadMessages(locale);
    }

    @Override
    public void disable() throws ModuleException {

    }

    @Override
    public String getName() {
        return ModuleEnum.LANG_MODULE.getName();
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
