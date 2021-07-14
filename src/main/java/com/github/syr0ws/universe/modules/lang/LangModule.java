package com.github.syr0ws.universe.modules.lang;

import com.github.syr0ws.universe.modules.Module;

public interface LangModule extends Module {

    LangModel getLangModel();

    LangService getLangService();
}
