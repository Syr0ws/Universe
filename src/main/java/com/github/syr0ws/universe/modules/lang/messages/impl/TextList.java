package com.github.syr0ws.universe.modules.lang.messages.impl;

import java.util.List;
import java.util.stream.Collectors;

public class TextList extends Text {

    private final List<String> list;

    public TextList(List<String> list) {

        if(list == null)
            throw new IllegalArgumentException("List cannot be null.");

        this.list = list;
    }

    public List<String> getList() {
        return this.list.stream().map(super::format).collect(Collectors.toList());
    }
}
