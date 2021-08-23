package com.github.syr0ws.universe.sdk.modules.view.handlers;

import com.github.syr0ws.universe.sdk.modules.view.ViewType;
import com.github.syr0ws.universe.sdk.modules.view.impl.DefaultViewType;
import com.github.syr0ws.universe.sdk.modules.view.views.ActionBarView;
import org.bukkit.plugin.Plugin;

public class ActionBarHandler extends AbstractViewHandler<ActionBarView> {

    private final Plugin plugin;
    private UpdateTask task;

    public ActionBarHandler(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void enable() {
        this.task = new UpdateTask();
        this.task.runTaskTimer(this.plugin, 0L, 20L);
    }

    @Override
    public void disable() {
        this.task.cancel();
        this.task = null;
    }

    @Override
    public ViewType getViewType() {
        return DefaultViewType.ACTION_BAR;
    }

    @Override
    public Class<ActionBarView> getViewClass() {
        return ActionBarView.class;
    }
}
