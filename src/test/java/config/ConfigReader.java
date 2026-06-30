package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private static final Properties properties = new Properties();
    private static final String CONFIG_FILE = "application.properties";

    static {
        loadProperties();
    }

    private ConfigReader() {
        throw new UnsupportedOperationException("ConfigReader class cannot be instantiated");
    }

    private static void loadProperties() {
        try (InputStream inputStream = ConfigReader.class.getClassLoader()
                .getResourceAsStream(CONFIG_FILE)) {

            if (inputStream == null) {
                throw new RuntimeException(CONFIG_FILE + " not found in classpath (src/test/resources)");
            }
            properties.load(inputStream);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load " + CONFIG_FILE, e);
        }
    }

    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property '" + key + "' not found in " + CONFIG_FILE);
        }
        return value;
    }

    public static String getBaseUrl() {
        return get("base.url");
    }
}
