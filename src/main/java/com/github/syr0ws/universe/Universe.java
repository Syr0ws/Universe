package com.github.syr0ws.universe;

import com.github.syr0ws.universe.modules.ModuleException;
import com.github.syr0ws.universe.modules.ModuleService;
import com.github.syr0ws.universe.modules.combat.impl.DefaultCombatModule;
import org.bukkit.Bukkit;

public class Universe extends Game {

    @Override
    public void onEnable() {

        super.saveDefaultConfig();

        ModuleService service = super.getModuleService();

        try { service.enableModule(new DefaultCombatModule(this));
        } catch (ModuleException e) { e.printStackTrace(); }

        Bukkit.getPluginManager().registerEvents(new UniverseListener(), this);
    }

    @Override
    public void onDisable() {

        ModuleService service = super.getModuleService();

        try { service.disableModule("CombatModule");
        } catch (ModuleException e) { e.printStackTrace(); }
    }
}
