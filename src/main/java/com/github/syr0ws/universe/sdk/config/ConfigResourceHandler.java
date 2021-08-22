package com.github.syr0ws.universe.sdk.config;

import java.io.IOException;
import java.nio.file.Path;

public interface ConfigResourceHandler<T> {

    void save(Path target) throws IOException;

    void addSubConfig(String key, String resource);

    T getDefaultConfiguration();
}
