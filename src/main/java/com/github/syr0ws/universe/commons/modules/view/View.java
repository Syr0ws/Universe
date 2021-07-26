package com.github.syr0ws.universe.commons.modules.view;

public interface View {

    int NORMAL_PRIORITY = 10;

    void set();

    void update();

    void remove();

    String getId();

    int getPriority();
}
