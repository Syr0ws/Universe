package com.github.syr0ws.universe.sdk.modules.interaction.impl;

import com.github.syr0ws.universe.sdk.modules.interaction.Interaction;
import com.github.syr0ws.universe.sdk.modules.interaction.InteractionType;
import com.github.syr0ws.universe.sdk.modules.interaction.Interactive;
import com.github.syr0ws.universe.sdk.modules.interaction.InteractiveData;

import java.util.*;

public class CraftInteractiveData implements InteractiveData {

    private final Map<InteractionType, Collection<Interactive<?>>> interactive = new HashMap<>();

    public CraftInteractiveData(Collection<Interactive<?>> collection) {
        this.init(collection);
    }

    private void init(Collection<Interactive<?>> collection) {

        for(Interactive<?> interactive : collection) {

            InteractionType type = interactive.getType();

            Collection<Interactive<?>> stored = this.interactive.getOrDefault(type, new ArrayList<>());
            stored.add(interactive);

            if(!this.interactive.containsKey(type)) this.interactive.put(type, stored);
        }
    }

    @Override
    public <T> Optional<Interactive<T>> getInteractive(Interaction<T> interaction) {

        InteractionType type = interaction.getType();

        Collection<Interactive<?>> collection = this.interactive.getOrDefault(type, Collections.emptyList());

        for(Interactive<?> interactive : collection) {

            Class<? extends Interactive<T>> clazz = interaction.getInteractiveClass();

            if(!clazz.isInstance(interactive)) continue;

            Interactive<T> casted = clazz.cast(interactive);

            if(casted.is(interaction.get())) return Optional.of(casted);
        }

        return Optional.empty();
    }
}
