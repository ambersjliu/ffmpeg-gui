package data_access.config;

import lombok.SneakyThrows;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Extends the AbstractConfigLoader class.
 * Loads the paths where ffmpeg.exe and ffprobe.exe are located
 * directly from the resources/application.properties file.
 */
public class PropertiesConfigLoader extends AbstractConfigLoader{
    private Properties properties = new Properties();

    @SneakyThrows
    public PropertiesConfigLoader() throws IOException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (inputStream == null){
                throw new FileNotFoundException("No application.properties file found");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            // TODO: idk whats a better thing to handle this error with ITS TEMPORARY
            e.printStackTrace();
        }
    }

    public String getFfmpegPath(){
        return properties.getProperty("ffmpeg.path");
    }

    public String getFfprobePath(){
        return properties.getProperty("ffprobe.path");
    }
}
