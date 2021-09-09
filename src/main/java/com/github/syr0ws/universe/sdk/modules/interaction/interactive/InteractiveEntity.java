package com.github.syr0ws.universe.sdk.modules.interaction.interactive;

import com.github.syr0ws.universe.sdk.modules.interaction.InteractionType;
import org.bukkit.entity.EntityType;

public class InteractiveEntity extends AbstractInteractive<EntityType> {

    private final EntityType type;

    public InteractiveEntity(EntityType type) {

        if(type == null)
            throw new IllegalArgumentException("EntityType cannot be null.");

        this.type = type;
    }

    @Override
    public boolean is(EntityType type) {
        return this.type.equals(type);
    }

    @Override
    public EntityType get() {
        return this.type;
    }

    @Override
    public Class<EntityType> getParamClass() {
        return EntityType.class;
    }

    @Override
    public InteractionType getType() {
        return InteractionType.ENTITY;
    }
}
