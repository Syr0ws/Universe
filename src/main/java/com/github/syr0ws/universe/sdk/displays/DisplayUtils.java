package com.github.syr0ws.universe.sdk.displays;

import com.github.syr0ws.universe.commons.modules.ModuleEnum;
import com.github.syr0ws.universe.commons.modules.ModuleService;
import com.github.syr0ws.universe.commons.modules.lang.LangModule;
import com.github.syr0ws.universe.commons.modules.lang.LangService;
import com.github.syr0ws.universe.sdk.Game;
import com.github.syr0ws.universe.sdk.displays.dao.ConfigDisplayDAO;
import com.github.syr0ws.universe.sdk.displays.dao.DisplayDAO;
import com.github.syr0ws.universe.sdk.displays.impl.SimpleDisplayFactory;
import com.github.syr0ws.universe.sdk.displays.impl.SimpleDisplayManager;
import com.github.syr0ws.universe.sdk.displays.types.TextDisplay;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public class DisplayUtils {

    public static DisplayManager getDisplayManager(Game game) {

        ModuleService service = game.getModuleService();

        Optional<LangModule> optional = service.getModule(ModuleEnum.LANG_MODULE.getName(), LangModule.class);
        LangService langService = optional.map(LangModule::getLangService).orElse(null);

        DisplayFactory factory = new SimpleDisplayFactory(langService);
        DisplayDAO dao = new ConfigDisplayDAO(factory, game.getConfig());

        return new SimpleDisplayManager(dao);
    }

    public static void addPlaceholders(Collection<Display> displays, Map<String, String> placeholders) {
        displays.stream()
                .filter(display -> display instanceof TextDisplay)
                .map(display -> (TextDisplay) display)
                .forEach(display -> display.addPlaceholders(placeholders));
    }

    public static void sendDisplays(Collection<Display> displays, Player player) {
        displays.forEach(display -> display.displayTo(player));
    }

    public static void sendDisplays(Collection<Display> displays, Collection<? extends Player> players) {
        players.forEach(player ->
                displays.forEach(display -> display.displayTo(player)));
    }
}
