package net.airymc.core.file;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUtils {

    public static InputStream getResource(String resource) {
        InputStream stream = FileUtils.class.getResourceAsStream("/" + resource);
        if (stream == null) {
            throw new RuntimeException("Resource does not exist: " + resource);
        }
        return stream;
    }

    public static void copyResource(String resource, String destination) {
        try (InputStream stream = getResource(resource)) {
            Files.copy(stream, Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean resourceExists(String resource) {
        return getResource(resource) != null;
    }
}
