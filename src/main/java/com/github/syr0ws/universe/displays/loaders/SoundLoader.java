package com.github.syr0ws.universe.displays.loaders;

import com.github.syr0ws.universe.displays.Display;
import com.github.syr0ws.universe.displays.DisplayLoader;
import com.github.syr0ws.universe.displays.types.SoundEffect;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;

public class SoundLoader implements DisplayLoader {

    @Override
    public Display load(ConfigurationSection section) {

        Sound sound = Sound.valueOf(section.getString("sound"));
        int volume = section.getInt("volume", 1);
        float pitch = (float) section.getDouble("pitch", 1);

        return new SoundEffect(sound, volume, pitch);
    }
}
