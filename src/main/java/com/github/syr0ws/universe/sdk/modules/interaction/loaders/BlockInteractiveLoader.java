package com.github.syr0ws.universe.sdk.modules.interaction.loaders;

import com.github.syr0ws.universe.sdk.modules.interaction.InteractionType;
import com.github.syr0ws.universe.sdk.modules.interaction.Interactive;
import com.github.syr0ws.universe.sdk.modules.interaction.InteractiveException;
import com.github.syr0ws.universe.sdk.modules.interaction.InteractiveLoader;
import com.github.syr0ws.universe.sdk.modules.interaction.interactive.InteractiveBlock;
import org.bukkit.Material;

public class BlockInteractiveLoader implements InteractiveLoader<Material> {

    @Override
    public Interactive<Material> load(String type) throws InteractiveException {

        Material material = Material.getMaterial(type);

        if(material == null)
            throw new InteractiveException(String.format("Invalid material '%s'.", type));

        if(!material.isBlock())
            throw new InteractiveException(String.format("Material '%s' is not a block.", type));

        return new InteractiveBlock(material);
    }

    @Override
    public InteractionType getType() {
        return InteractionType.BLOCK;
    }
}
