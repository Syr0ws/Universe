package com.github.syr0ws.universe.sdk.config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

public class YamlConfigResourceHandler implements ConfigResourceHandler<YamlConfiguration> {

    private final Plugin plugin;
    private final Map<String, String> resources = new LinkedHashMap<>();

    public YamlConfigResourceHandler(Plugin plugin) {

        if(plugin == null)
            throw new IllegalArgumentException("Plugin cannot be null.");

        this.plugin = plugin;
    }

    @Override
    public void save(Path target) throws IOException {

        if(target == null)
            throw new IllegalArgumentException("Target path cannot be null.");

        String content = this.concatAll();

        Path parent = target.getParent();

        if(parent != null) Files.createDirectories(parent);

        Files.write(target, content.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void addSubConfig(String key, String resource) {

        if(key == null)
            throw new IllegalArgumentException("Key cannot be null.");

        if(resource == null)
            throw new IllegalArgumentException("Resource cannot be null.");

        this.resources.put(key, resource);
    }

    @Override
    public YamlConfiguration getDefaultConfiguration() {

        String content = this.concatAll();

        YamlConfiguration config = new YamlConfiguration();

        try { config.loadFromString(content);
        } catch (InvalidConfigurationException e) { e.printStackTrace(); }

        return config;
    }

    private String getResourceContent(String resource) throws IOException {

        InputStream stream = this.plugin.getResource(resource);

        if(stream == null)
            throw new IOException(String.format("Resource '%s' not found.", resource));

        InputStreamReader streamReader = new InputStreamReader(stream);
        BufferedReader reader = new BufferedReader(streamReader);

        StringBuilder builder = new StringBuilder();

        String line;
        while((line = reader.readLine()) != null) {

            if(builder.length() > 0) builder.append("\n");

            builder.append(line);
        }

        streamReader.close();

        return builder.toString();
    }

    private String concatAll() {

        StringBuilder builder = new StringBuilder();

        for(Map.Entry<String, String> entry : this.resources.entrySet()) {

            String resource = entry.getValue();

            try {

                String content = this.getResourceContent(resource);

                if(builder.length() > 0) builder.append("\n\n");

                builder.append(content);

            } catch (IOException e) { e.printStackTrace(); }
        }
        return builder.toString();
    }
}
