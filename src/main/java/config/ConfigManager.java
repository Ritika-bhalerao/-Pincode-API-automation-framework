package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

    private static final Properties properties = new Properties();

    static {
        try {
            String filePath = "src/test/java/resources/config.properties";

            FileInputStream file = new FileInputStream(filePath);

            properties.load(file);

            file.close();

        } catch (IOException e) {
            throw new RuntimeException(
                    "Unable to load config.properties", e
            );
        }
    }

    // Get any property by key
    public static String getProperty(String key) {

        String value = properties.getProperty(key);

        if (value == null || value.trim().isEmpty()) {
            throw new RuntimeException(
                    "Missing configuration property: " + key
            );
        }

        return value.trim();
    }

    // Get application base URL
    public static String getBaseUrl() {
        return getProperty("base.url");
    }
}