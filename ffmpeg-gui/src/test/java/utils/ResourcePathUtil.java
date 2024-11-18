package utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResourcePathUtil {
    public static String getResourceFilePath(String resourceName) throws IOException, URISyntaxException {
        ClassLoader classLoader = ResourcePathUtil.class.getClassLoader();
        URL resource = classLoader.getResource(resourceName);

        if (resource == null) {
            throw new IOException("Not found: " + resourceName);
        }

        Path path = Paths.get(resource.toURI());
        return path.toString();
    }
}
