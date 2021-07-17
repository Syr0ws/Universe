package com.github.syr0ws.universe.displays.dao;

import com.github.syr0ws.universe.displays.Display;
import com.github.syr0ws.universe.displays.DisplayException;
import com.github.syr0ws.universe.displays.DisplayFactory;
import com.github.syr0ws.universe.displays.impl.SimpleDisplayFactory;
import com.github.syr0ws.universe.displays.loaders.ActionBarLoader;
import com.github.syr0ws.universe.displays.loaders.MessageLoader;
import com.github.syr0ws.universe.displays.loaders.SoundLoader;
import com.github.syr0ws.universe.displays.loaders.TitleLoader;
import com.github.syr0ws.universe.modules.lang.LangService;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ConfigDisplayDAO implements DisplayDAO {

    private final ConfigurationSection section;
    private final DisplayFactory factory;

    public ConfigDisplayDAO(DisplayFactory factory, ConfigurationSection section) {

        if(section == null)
            throw new IllegalArgumentException("ConfigurationSection cannot be null.");

        this.factory = factory;
        this.section = section;
    }

    @Override
    public Display getDisplay(String path) throws DisplayException {

        ConfigurationSection section = this.section.getConfigurationSection(path);

        if(section == null)
            throw new DisplayException(String.format("No display section found at '%s.%s'.", this.section.getName(), path));

        return this.factory.getDisplay(section);
    }

    @Override
    public Collection<Display> getDisplays(String path) throws DisplayException {

        ConfigurationSection section = this.section.getConfigurationSection(path);

        if(section == null)
            throw new DisplayException(String.format("No display section found at '%s.%s'.", this.section.getName(), path));

        List<Display> displays = new ArrayList<>();

        for(String key : section.getKeys(false)) {

            ConfigurationSection displaySection = section.getConfigurationSection(key);

            if(displaySection == null)
                throw new DisplayException(String.format("Invalid display section found at '%s.%s'.", section.getCurrentPath(), key));

            Display display = this.factory.getDisplay(displaySection);

            displays.add(display);
        }
        return displays;
    }

    public ConfigurationSection getSection() {
        return this.section;
    }

    public DisplayFactory getFactory() {
        return this.factory;
    }
}
