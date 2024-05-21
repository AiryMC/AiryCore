package net.airymc.core.file;

public abstract class BaseConfig {

    private final String path;

    protected BaseConfig(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
