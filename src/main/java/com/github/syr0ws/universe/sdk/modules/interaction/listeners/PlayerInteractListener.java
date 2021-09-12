package com.github.syr0ws.universe.sdk.modules.interaction.listeners;

import com.github.syr0ws.universe.sdk.modules.interaction.Interaction;
import com.github.syr0ws.universe.sdk.modules.interaction.InteractionType;
import com.github.syr0ws.universe.sdk.modules.interaction.InteractiveArea;
import com.github.syr0ws.universe.sdk.modules.interaction.InteractiveAreaModel;
import com.github.syr0ws.universe.sdk.modules.interaction.events.AreaInteractionEvent;
import com.github.syr0ws.universe.sdk.modules.interaction.impl.CraftInteraction;
import com.github.syr0ws.universe.sdk.modules.interaction.interactive.InteractiveBlock;
import com.github.syr0ws.universe.sdk.modules.interaction.interactive.InteractiveEntity;
import com.github.syr0ws.universe.sdk.modules.interaction.interactive.InteractiveItem;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Comparator;
import java.util.Optional;

public class PlayerInteractListener implements Listener {

    private final InteractiveAreaModel model;

    public PlayerInteractListener(InteractiveAreaModel model) {

        if(model == null)
            throw new IllegalArgumentException("InteractiveAreaModel cannot be null.");

        this.model = model;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerInteract(PlayerInteractEvent event) {

        if(event.hasBlock()) this.onBlockInteract(event);

        if(event.hasItem()) this.onItemInteract(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {

        this.onEntityInteract(event);
    }

    private void onBlockInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        Location location = block.getLocation();

        Interaction<Material> interaction = new CraftInteraction<>(player, location, InteractionType.BLOCK, block.getType(), InteractiveBlock.class);

        boolean allowed = this.isInteractionAllowed(interaction);

        event.setUseInteractedBlock(allowed ? Event.Result.ALLOW : Event.Result.DENY);
    }

    private void onItemInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        Location location = player.getLocation();

        Interaction<Material> interaction = new CraftInteraction<>(player, location, InteractionType.ITEM, item.getType(), InteractiveItem.class);

        boolean allowed = this.isInteractionAllowed(interaction);

        event.setUseItemInHand(allowed ? Event.Result.ALLOW : Event.Result.DENY);
    }

    private void onEntityInteract(PlayerInteractEntityEvent event) {

        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        Location location = entity.getLocation();

        Interaction<?> interaction = new CraftInteraction<>(player, location, InteractionType.ENTITY, entity.getType(), InteractiveEntity.class);

        boolean allowed = this.isInteractionAllowed(interaction);

        event.setCancelled(allowed);
    }

    private boolean isInteractionAllowed(Interaction<?> interaction) {

        Optional<InteractiveArea> optional = this.model.getArea().stream()
                .filter(area -> area.isIn(interaction.getLocation()))
                .max(Comparator.comparingInt(area -> area.getPriority().ordinal()));

        if(!optional.isPresent()) return true;

        InteractiveArea area = optional.get();
        boolean allowed = area.isAllowed(interaction);

        AreaInteractionEvent event = new AreaInteractionEvent(area, interaction);
        event.setCancelled(allowed);

        Bukkit.getPluginManager().callEvent(event);

        return event.isCancelled();
    }
}
