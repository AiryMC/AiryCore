package net.airymc.core.velocity.config;

import net.airymc.core.file.BaseConfig;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class Config extends BaseConfig {

    private final Yaml yaml;

    public Config(String path) {
        super(path);

        yaml = new Yaml();
        // Load yaml file from the path inside the data folder
    }
}
