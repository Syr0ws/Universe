package com.github.syr0ws.universe.modules.scoreboard;

import java.util.Collection;
import java.util.Optional;

public interface ScoreboardCreatorManager {

    void addCreator(ScoreboardCreator creator);

    void removeCreator(ScoreboardCreator creator);

    boolean exists(ScoreboardCreator creator);

    Optional<? extends ScoreboardCreator> getCreator(ScoreboardType type);

    Collection<? extends ScoreboardCreator> getCreators();
}
