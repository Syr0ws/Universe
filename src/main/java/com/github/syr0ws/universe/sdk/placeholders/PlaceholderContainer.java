package com.github.syr0ws.universe.sdk.placeholders;

import com.github.syr0ws.universe.sdk.utils.TextUtils;

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

    public void addPlaceholders(Map<String, String> placeholders) {

        if(placeholders == null)
            throw new IllegalArgumentException("Map cannot be null.");

        this.placeholders.putAll(placeholders);
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
