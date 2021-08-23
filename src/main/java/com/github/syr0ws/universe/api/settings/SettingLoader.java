package com.github.syr0ws.universe.api.settings;

import com.github.syr0ws.universe.api.settings.Setting;

import java.util.Collection;

public interface SettingLoader {

    void load(Collection<Setting<?>> settings);
}
