package com.github.syr0ws.universe.commons.modules.view.handlers;

import com.github.syr0ws.universe.commons.modules.view.View;
import com.github.syr0ws.universe.commons.modules.view.ViewException;
import com.github.syr0ws.universe.commons.modules.view.ViewHandler;
import com.github.syr0ws.universe.sdk.tools.Task;
import org.bukkit.entity.Player;

import java.util.*;

public abstract class AbstractViewHandler<T extends View> implements ViewHandler<T> {

    private final Map<Player, List<T>> views = new HashMap<>();

    @Override
    public void enable() {}

    @Override
    public void disable() {}

    @Override
    public void addView(Player player, T view) {

        if(view == null)
            throw new IllegalArgumentException("View cannot be null.");

        // There cannot be two views with the same id.
        if(this.hasView(player, view.getId()))
            throw new ViewException(String.format("A view with the id '%s' already exists.", view.getId()));

        // Reusable variable.
        boolean hasViews = this.hasViews(player);

        List<T> views = hasViews ? this.views.get(player) : new ArrayList<>();

        // Retrieving the current view if it exists.
        Optional<T> optional = hasViews ? Optional.of(views.get(0)) : Optional.empty();

        views.add(view);
        views.sort(Comparator.comparingInt(View::getPriority)); // Sorting views by priority.

        // Storing data in cache.
        if(hasViews) this.views.replace(player, views);
        else this.views.put(player, views);

        // Checking if the new view is now the current one.
        if(this.isPriorityView(player, view)) {

            // Removing the old view.
            optional.ifPresent(View::remove);

            // Setting the new view.
            view.set();
        }
    }

    @Override
    public void removeView(Player player, T view) {

        if(view == null)
            throw new IllegalArgumentException("View cannot be null.");

        this.removeView(player, view.getId());
    }

    @Override
    public void removeView(Player player, String id) {

        if(!this.hasViews(player))
            throw new ViewException("Player doesn't have view.");

        List<T> views = this.views.getOrDefault(player, Collections.emptyList());

        T view = views.stream()
                .filter(stored -> stored.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElseThrow(() -> new ViewException(String.format("Player doesn't have view with id '%s'.", id)));

        // If the view is currently active, removing it.
        if(this.isPriorityView(player, view)) view.remove();

        views.remove(view);

        // If there is no more views, removing player data.
        if(views.size() == 0) this.views.remove(player);
    }

    @Override
    public void removeViews(Player player) {

        if(!this.hasViews(player))
            throw new ViewException("Player doesn't have views.");

        List<T> views = new ArrayList<>(this.views.get(player));
        views.forEach(view -> this.removeView(player, view));
    }

    @Override
    public void removeViews() {
        this.views.keySet().forEach(this::removeViews);
    }

    @Override
    public boolean hasView(Player player, String id) {
        List<T> views = this.views.getOrDefault(player, Collections.emptyList());
        return views.stream().anyMatch(view -> view.getId().equalsIgnoreCase(id));
    }

    @Override
    public boolean hasViews(Player player) {
        return this.views.containsKey(player);
    }

    @Override
    public Collection<T> getViews(Player player) {
        return this.views.getOrDefault(player, Collections.emptyList());
    }

    @Override
    public Map<Player, Collection<T>> getViews() {
        return Collections.unmodifiableMap(this.views);
    }

    private boolean isPriorityView(Player player, View view) {
        List<T> views = this.views.get(player);
        return views.get(0).equals(view);
    }

    private T getPriorityView(Player player) {
        List<T> views = this.views.get(player);
        return views.get(0);
    }

    public class UpdateTask extends Task {

        @Override
        public void run() {
            AbstractViewHandler.this.views.keySet().stream()
                    .map(AbstractViewHandler.this::getPriorityView)
                    .filter(View::isUpdatable)
                    .forEach(View::update);
        }
    }
}
