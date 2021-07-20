package com.github.syr0ws.universe.commons.modules.lang;

import com.github.syr0ws.universe.commons.modules.Module;

public interface LangModule extends Module {

    LangModel getLangModel();

    LangService getLangService();
}
