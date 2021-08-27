package com.github.syr0ws.universe.sdk.modules.lang.messages.impl;

import com.github.syr0ws.universe.sdk.modules.lang.messages.Message;
import com.github.syr0ws.universe.sdk.placeholders.PlaceholderContainer;

import java.util.List;
import java.util.stream.Collectors;

public class TextList extends PlaceholderContainer implements Message {

    private final List<String> list;

    public TextList(List<String> list) {

        if(list == null)
            throw new IllegalArgumentException("List cannot be null.");

        this.list = list;
    }

    public List<String> getList() {
        return this.list.stream().map(this::format).collect(Collectors.toList());
    }
}
