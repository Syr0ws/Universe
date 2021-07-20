package com.github.syr0ws.universe.commons.modules.combat.events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GamePlayerDeathEvent extends PlayerEvent {

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    private boolean keepInventory, keepExp, keepPotionEffects;
    private int droppedExp;

    private final List<ItemStack> drops;

    public GamePlayerDeathEvent(Player player) {
        super(player);

        PlayerInventory inventory = player.getInventory();

        this.drops = new ArrayList<>();
        this.drops.addAll(Arrays.asList(inventory.getContents()));
        this.drops.addAll(Arrays.asList(inventory.getArmorContents()));
    }

    public boolean getKeepInventory() {
        return this.keepInventory;
    }

    public void setKeepInventory(boolean keepInventory) {
        this.keepInventory = keepInventory;
    }

    public boolean getKeepExp() {
        return keepExp;
    }

    public void setKeepExp(boolean keepExp) {
        this.keepExp = keepExp;
    }

    public boolean getKeepPotionEffects() {
        return keepPotionEffects;
    }

    public void setKeepPotionEffects(boolean keepPotionEffects) {
        this.keepPotionEffects = keepPotionEffects;
    }

    public int getDroppedExp() {
        return droppedExp;
    }

    public void setDroppedExp(int droppedExp) {

        if(droppedExp < 0)
            throw new IllegalArgumentException("Dropped exp cannot be negative.");

        this.droppedExp = droppedExp;
    }

    public List<ItemStack> getDrops() {
        return this.drops;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }
}
