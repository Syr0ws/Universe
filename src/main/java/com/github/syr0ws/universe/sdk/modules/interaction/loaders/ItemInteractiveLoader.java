package com.github.syr0ws.universe.sdk.modules.interaction.loaders;

import com.github.syr0ws.universe.sdk.modules.interaction.InteractionType;
import com.github.syr0ws.universe.sdk.modules.interaction.Interactive;
import com.github.syr0ws.universe.sdk.modules.interaction.InteractiveException;
import com.github.syr0ws.universe.sdk.modules.interaction.InteractiveLoader;
import com.github.syr0ws.universe.sdk.modules.interaction.interactive.InteractiveItem;
import org.bukkit.Material;

public class ItemInteractiveLoader implements InteractiveLoader<Material> {

    @Override
    public Interactive<Material> load(String type) throws InteractiveException {

        Material material = Material.getMaterial(type);

        if(material == null)
            throw new InteractiveException(String.format("Invalid material '%s'.", type));

        return new InteractiveItem(material);
    }

    @Override
    public InteractionType getType() {
        return InteractionType.ITEM;
    }
}
