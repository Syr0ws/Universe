package com.github.syr0ws.universe.utils;

import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {

    public static void copyResource(Plugin plugin, Path target, String resource) throws IOException {

        InputStream stream = plugin.getResource(resource);

        if(stream == null)
            throw new IOException(String.format("Resource '%s' not found.", resource));

        Files.copy(stream, target);
    }
}
