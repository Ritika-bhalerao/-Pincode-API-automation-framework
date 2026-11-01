package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input =
                     ConfigReader.class.getClassLoader()
                             .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new IllegalStateException(
                        "config.properties was not found");
            }

            PROPERTIES.load(input);

        } catch (IOException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private ConfigReader() {
    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

    public static String getBaseUrl() {
        return getProperty("base.prod.url");
    }
}