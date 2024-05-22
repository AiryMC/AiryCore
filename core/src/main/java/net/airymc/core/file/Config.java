package net.airymc.core.file;

import dev.dejvokep.boostedyaml.YamlDocument;
import dev.dejvokep.boostedyaml.route.Route;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Config {

    private final String path;
    private File file;
    private YamlDocument document;

    public Config(String internalPath, String destinationPath) {
        this.path = destinationPath;
        this.file = new File("plugins", destinationPath);

        if (!file.exists()) {
            file.mkdirs();

            if (FileUtils.resourceExists(internalPath) && !destinationPath.isEmpty())
                FileUtils.copyResource(internalPath, "plugins/" + destinationPath);
            else {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        try {
            document = YamlDocument.create(file, Objects.requireNonNull(this.getClass().getResourceAsStream("/" + internalPath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Config(String destinationPath) {
        this("", destinationPath);
    }

    public void save() {
        try {
            document.save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void reload() {
        try {
            document.reload();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T get(String key) {
        return (T) document.get(Route.from(key));
    }

    public <T> void set(String key, T value) {
        document.set(Route.from(key), value);
        save();
    }

    public <T> void setDefault(String key, T value) {
        if (get(key) == null) {
            document.set(Route.from(key), value);
            save();
        }
    }

    public boolean has(String key) {
        return get(key) != null;
    }

    public String getPath() {
        return path;
    }

    public File getFile() {
        return file;
    }

    public YamlDocument getDocument() {
        return document;
    }
}
