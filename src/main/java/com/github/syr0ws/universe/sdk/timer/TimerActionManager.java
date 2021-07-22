package com.github.syr0ws.universe.sdk.timer;

import java.util.*;

public class TimerActionManager {

    private final Map<Integer, List<TimerAction>> actions = new HashMap<>();

    public void addAction(int time, TimerAction action) {

        if(time < -1)
            throw new IllegalArgumentException("Invalid time.");

        List<TimerAction> actions = this.getActions(time);
        actions.add(action);

        if(this.hasActions(time)) this.actions.replace(time, actions);
        else this.actions.put(time, actions);
    }

    public void removeActions(int time) {
        this.actions.remove(time);
    }

    public void executeActions(int time) {

        // Executing repeatable actions.
        this.getActions(-1).forEach(TimerAction::execute);

        // Executing actions for the specified time.
        this.getActions(time).forEach(TimerAction::execute);
    }

    public boolean hasActions(int time) {
        return this.actions.containsKey(time);
    }

    public List<TimerAction> getActions(int time) {
        return this.actions.getOrDefault(time, new ArrayList<>());
    }

    public Map<Integer, List<TimerAction>> getActions() {
        return Collections.unmodifiableMap(this.actions);
    }
}
