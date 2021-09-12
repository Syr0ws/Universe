package com.github.syr0ws.universe.sdk.game.settings.types;

import com.github.syr0ws.universe.api.settings.SettingFilter;
import com.github.syr0ws.universe.api.settings.SettingValidationException;
import com.github.syr0ws.universe.sdk.game.settings.types.builder.MutableSettingBuilder;

public class MutableSetting<T> extends AbstractSetting<T> implements com.github.syr0ws.universe.api.settings.MutableSetting<T> {

    private final T defaultValue;
    private final SettingFilter<T> filter;
    private T value;

    protected MutableSetting(String name, T defaultValue, T value, SettingFilter<T> filter) {
        super(name);

        this.filter = filter;

        this.validate(defaultValue);
        if(value != null) this.validate(value);

        this.defaultValue = defaultValue;
        this.value = value == null ? defaultValue : value;
    }

    private void validate(T value) {

        if(this.filter != null && ! this.filter.filter(value))
            throw new SettingValidationException("Invalid setting value.");
    }

    public void setValue(T value) {
        this.validate(value);
        this.value = value;
    }

    @Override
    public T getDefaultValue() {
        return this.defaultValue;
    }

    @Override
    public SettingFilter<T> getFilter() {
        return this.filter;
    }

    @Override
    public T getValue() {
        return this.value;
    }

    public static class Builder<T> extends MutableSettingBuilder<T, MutableSetting<T>, Builder<T>> {

        public Builder(String name, T defaultValue) {
            super(name, defaultValue);
        }

        @Override
        public Builder<T> self() {
            return this;
        }

        @Override
        public MutableSetting<T> build() {
            return new MutableSetting<>(super.getName(), super.getDefaultValue(), super.getValue(), super.getFilter());
        }
    }
}
