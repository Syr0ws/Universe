package com.github.syr0ws.universe.sdk.modules.interaction.interactive;

import com.github.syr0ws.universe.sdk.modules.interaction.InteractionType;
import org.bukkit.Material;

public class InteractiveBlock extends AbstractInteractive<Material> {

    private final Material material;

    public InteractiveBlock(Material material) {

        if(material == null)
            throw new IllegalArgumentException("Material cannot be null.");

        if(!material.isBlock())
            throw new IllegalArgumentException(String.format("Invalid material '%s'.", material.name()));

        this.material = material;
    }

    @Override
    public boolean is(Material material) {
        return this.material.equals(material);
    }

    @Override
    public Material get() {
        return this.material;
    }

    @Override
    public Class<Material> getParamClass() {
        return Material.class;
    }

    @Override
    public InteractionType getType() {
        return InteractionType.BLOCK;
    }
}
