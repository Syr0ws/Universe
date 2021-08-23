package com.github.syr0ws.universe.sdk.modules.view;

import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Map;

public interface ViewHandler<T extends View> {

    void enable();

    void disable();

    void addView(Player player, T view);

    void removeView(Player player, T view);

    void removeView(Player player, String id);

    void removeViews(Player player);

    void removeViews();

    boolean hasView(Player player, String id);

    boolean hasViews(Player player);

    ViewType getViewType();

    Class<T> getViewClass();

    Collection<T> getViews(Player player);

    Map<Player, Collection<T>> getViews();
}
