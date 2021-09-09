package com.github.syr0ws.universe.sdk.modules.interaction;

import com.github.syr0ws.universe.sdk.modules.interaction.loaders.BlockInteractiveLoader;
import com.github.syr0ws.universe.sdk.modules.interaction.loaders.EntityInteractiveLoader;
import com.github.syr0ws.universe.sdk.modules.interaction.loaders.ItemInteractiveLoader;

import java.util.ArrayList;
import java.util.Collection;

public class InteractiveFactory {

    private static final Collection<InteractiveLoader<?>> LOADERS = new ArrayList<>();

    static {
        LOADERS.add(new BlockInteractiveLoader());
        LOADERS.add(new ItemInteractiveLoader());
        LOADERS.add(new EntityInteractiveLoader());
    }

    public static <T> Interactive<T> getInteractive(InteractionType type, String name) throws InteractiveException {

        @SuppressWarnings("unchecked")
        InteractiveLoader<T> loader = (InteractiveLoader<T>) LOADERS.stream()
                .filter(stored -> stored.getType().equals(type)).findFirst()
                .orElseThrow(() -> new InteractiveException("No InteractiveLoader found."));

        return loader.load(name);
    }
}
