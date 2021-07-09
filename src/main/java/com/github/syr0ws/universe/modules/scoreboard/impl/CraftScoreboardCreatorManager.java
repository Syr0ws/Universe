package com.github.syr0ws.universe.modules.scoreboard.impl;

import com.github.syr0ws.universe.modules.scoreboard.ScoreboardCreator;
import com.github.syr0ws.universe.modules.scoreboard.ScoreboardCreatorManager;
import com.github.syr0ws.universe.modules.scoreboard.ScoreboardType;

import java.util.*;

public class CraftScoreboardCreatorManager implements ScoreboardCreatorManager {

    private final List<ScoreboardCreator> creators = new ArrayList<>();

    @Override
    public void addCreator(ScoreboardCreator creator) {

        if(creator == null)
            throw new IllegalArgumentException("ScoreboardCreator cannot be null.");

        if(this.creators.contains(creator))
            throw new IllegalArgumentException("ScoreboardCreator already contained.");

        this.creators.add(creator);
    }

    @Override
    public void removeCreator(ScoreboardCreator creator) {

        if(creator == null)
            throw new IllegalArgumentException("ScoreboardCreator cannot be null.");

        this.creators.remove(creator);
    }

    @Override
    public boolean exists(ScoreboardCreator creator) {
        return this.creators.contains(creator);
    }

    @Override
    public Optional<? extends ScoreboardCreator> getCreator(ScoreboardType type) {
        return this.creators.stream()
                .filter(creator -> creator.getType().equals(type))
                .findFirst();
    }

    @Override
    public Collection<? extends ScoreboardCreator> getCreators() {
        return Collections.unmodifiableCollection(this.creators);
    }
}
