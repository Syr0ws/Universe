package com.github.syr0ws.universe.placeholders;

import com.github.syr0ws.universe.utils.TextUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PlaceholderContainer {

    private final Map<String, String> placeholders = new HashMap<>();

    protected String format(String text) {

        for(Map.Entry<String, String> entry : this.placeholders.entrySet()) {
            text = text.replace(entry.getKey(), entry.getValue());
        }
        return TextUtils.parseColors(text);
    }

    public void addPlaceholder(String placeholder, String value) {

        if(placeholder == null)
            throw new IllegalArgumentException("Placeholder cannot be null.");

        if(value == null)
            throw new IllegalArgumentException("Value cannot be null.");

        this.placeholders.put(placeholder, value);
    }

    public void removePlaceholder(String placeholder) {

        if(placeholder == null)
            throw new IllegalArgumentException("Placeholder cannot be null.");

        this.placeholders.remove(placeholder);
    }

    public Map<String, String> getPlaceholders() {
        return Collections.unmodifiableMap(this.placeholders);
    }
}
