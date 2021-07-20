package com.github.syr0ws.universe.sdk.settings.dao;

import com.github.syr0ws.universe.sdk.settings.Setting;

import java.util.Collection;

public interface SettingLoader {

    void load(Collection<Setting<?>> settings);
}
