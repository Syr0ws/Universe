package com.github.syr0ws.universe.sdk.listeners;

import com.github.syr0ws.universe.api.settings.MutableSetting;
import com.github.syr0ws.universe.sdk.game.mode.DefaultModeType;
import com.github.syr0ws.universe.api.game.settings.GameSettings;
import com.github.syr0ws.universe.sdk.events.GamePlayerJoinEvent;
import com.github.syr0ws.universe.api.game.controller.GameController;
import com.github.syr0ws.universe.api.game.model.GameModel;
import com.github.syr0ws.universe.api.game.model.GamePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerPortalEvent;

public class GamePreRunningListener implements Listener {

    private final GameModel model;
    private final GameController controller;

    public GamePreRunningListener(GameModel model, GameController controller) {

        if(model == null)
            throw new IllegalArgumentException("GameModel cannot be null.");

        if(controller == null)
            throw new IllegalArgumentException("GameController cannot be null.");

        this.model = model;
        this.controller = controller;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerPreLogin(AsyncPlayerPreLoginEvent event) {

        GameSettings settings = this.model.getSettings();
        MutableSetting<Integer> setting = settings.getMaxPlayersSetting();

        int players = this.model.getOnlinePlayers().size();

        // Max number of online players not reached.
        if(players < setting.getValue()) return;

        // Kicking the player.
        event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_FULL);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onGamePlayerJoin(GamePlayerJoinEvent event) {

        GamePlayer player = event.getGamePlayer();

        this.controller.setMode(player, DefaultModeType.WAITING);

        player.getPlayer().teleport(this.model.getSpawn());
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onEntityDamage(EntityDamageEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onEntitySpawn(EntitySpawnEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerPortalTravel(PlayerPortalEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onEntityExplode(EntityExplodeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onInventoryClick(InventoryClickEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onInventoryDrag(InventoryDragEvent event) {
        event.setCancelled(true);
    }
}
