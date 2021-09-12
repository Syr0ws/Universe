package com.github.syr0ws.universe.sdk.game.settings.types;

import com.github.syr0ws.universe.api.settings.SettingFilter;
import com.github.syr0ws.universe.api.settings.SettingValidationException;
import com.github.syr0ws.universe.sdk.game.settings.types.builder.ConfigSettingBuilder;
import org.bukkit.configuration.ConfigurationSection;

public class CharacterSetting extends ConfigSetting<Character> {

    protected CharacterSetting(String name, Character defaultValue, Character value, SettingFilter<Character> filter, String path) {
        super(name, defaultValue, value, filter, path);
    }

    @Override
    public void read(ConfigurationSection section) {

        String string = section.getString(super.getPath(), "");

        if(string.length() != 1)
            throw new SettingValidationException(String.format("No character found at '%s.%s'.", section.getName(), super.getPath()));

        super.setValue(string.charAt(0));
    }

    public static class Builder extends ConfigSettingBuilder<Character, CharacterSetting, Builder> {

        public Builder(String name, Character defaultValue, String path) {
            super(name, defaultValue, path);
        }

        @Override
        public Builder self() {
            return this;
        }

        @Override
        public CharacterSetting build() {
            return new CharacterSetting(super.getName(), super.getDefaultValue(), super.getValue(), super.getFilter(), super.getPath());
        }
    }
}
