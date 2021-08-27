package com.github.syr0ws.universe.sdk.modules.lang;

import com.github.syr0ws.universe.api.modules.Module;

public interface LangModule extends Module {

    LangModel getLangModel();

    LangService getLangService();
}
