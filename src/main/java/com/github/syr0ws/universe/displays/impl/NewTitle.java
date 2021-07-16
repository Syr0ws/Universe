package com.github.syr0ws.universe.displays.impl;

import com.github.syr0ws.universe.modules.lang.LangService;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class NewTitle extends Title {

    public NewTitle(String title, String subtitle) {
        super(title, subtitle);
    }

    public NewTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        super(title, subtitle, fadeIn, stay, fadeOut);
    }

    public NewTitle(LangService service, String title, String subtitle) {
        super(service, title, subtitle);
    }

    public NewTitle(LangService service, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        super(service, title, subtitle, fadeIn, stay, fadeOut);
    }

    @Override
    public void displayTo(Player player) {

        String title = super.getTitle();
        String subtitle = super.getSubtitle();

        int fadeIn = super.getFadeIn();
        int fadeOut = super.getFadeOut();
        int stay = super.getStay();

        player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
    }

    @Override
    public void displayAll() {
        Bukkit.getOnlinePlayers().forEach(this::displayTo);
    }
}
