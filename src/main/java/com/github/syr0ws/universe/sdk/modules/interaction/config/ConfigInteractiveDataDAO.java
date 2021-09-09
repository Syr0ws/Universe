package com.github.syr0ws.universe.sdk.modules.interaction.config;

import com.github.syr0ws.universe.sdk.modules.interaction.*;
import com.github.syr0ws.universe.sdk.modules.interaction.impl.CraftInteractiveData;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;

public class ConfigInteractiveDataDAO implements InteractiveDataDAO {

    private final ConfigurationSection section;

    public ConfigInteractiveDataDAO(ConfigurationSection section) {

        if(section == null)
            throw new IllegalArgumentException("ConfigurationSection cannot be null.");

        this.section = section;
    }

    @Override
    public InteractiveData load(String path) {

        if(path == null)
            throw new IllegalArgumentException("Path cannot be null.");

        ConfigurationSection section = this.section.getConfigurationSection(path);

        if(section == null)
            throw new IllegalArgumentException(String.format("Invalid path '%s'.", path));

        List<Interactive<Material>> blocks = this.loadInteractiveElements(section,"blocks", InteractionType.BLOCK);
        List<Interactive<Material>> items = this.loadInteractiveElements(section, "items", InteractionType.ITEM);
        List<Interactive<EntityType>> entities = this.loadInteractiveElements(section,"entities", InteractionType.ENTITY);

        List<Interactive<?>> list = new ArrayList<>();
        list.addAll(blocks);
        list.addAll(items);
        list.addAll(entities);

        return new CraftInteractiveData(list);
    }

    protected <T> List<Interactive<T>> loadInteractiveElements(ConfigurationSection section, String key, InteractionType type) {

        if(!section.isSet(key))
            return new ArrayList<>();

        if(!section.isList(key))
            throw new IllegalArgumentException(String.format("Field '%s' must be a list in '%s'.", key, section.getCurrentPath()));

        List<Interactive<T>> list = new ArrayList<>();

        for(String value : section.getStringList(key)) {

            try {

                Interactive<T> interactive = InteractiveFactory.getInteractive(type, value);
                list.add(interactive);

            } catch (InteractiveException e) { e.printStackTrace(); }
        }
        return list;
    }
}
