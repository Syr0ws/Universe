package com.github.syr0ws.universe.sdk.modules.border;

import com.github.syr0ws.universe.api.modules.Module;

public interface BorderModule extends Module {

    BorderModel getBorderModel();

    BorderService getBorderService();
}
