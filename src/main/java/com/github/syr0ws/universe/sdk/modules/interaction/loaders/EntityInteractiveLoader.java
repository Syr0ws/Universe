package com.github.syr0ws.universe.sdk.modules.interaction.loaders;

import com.github.syr0ws.universe.sdk.modules.interaction.InteractionType;
import com.github.syr0ws.universe.sdk.modules.interaction.Interactive;
import com.github.syr0ws.universe.sdk.modules.interaction.InteractiveException;
import com.github.syr0ws.universe.sdk.modules.interaction.InteractiveLoader;
import com.github.syr0ws.universe.sdk.modules.interaction.interactive.InteractiveEntity;
import org.bukkit.entity.EntityType;

public class EntityInteractiveLoader implements InteractiveLoader<EntityType> {

    @Override
    public Interactive<EntityType> load(String type) throws InteractiveException {

        EntityType entityType;

        try { entityType = EntityType.valueOf(type);
        } catch (IllegalArgumentException e) { throw new InteractiveException(String.format("Invalid EntityType '%s'.", type)); }

        return new InteractiveEntity(entityType);
    }

    @Override
    public InteractionType getType() {
        return InteractionType.ENTITY;
    }
}
