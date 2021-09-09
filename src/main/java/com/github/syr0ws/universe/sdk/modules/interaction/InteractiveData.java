package com.github.syr0ws.universe.sdk.modules.interaction;

import java.util.Optional;

public interface InteractiveData {

    <T> Optional<Interactive<T>> getInteractive(Interaction<T> interaction);
}
