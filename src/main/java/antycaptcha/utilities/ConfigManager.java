package antycaptcha.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {


    private static final String CONFIG_PATH = "src/test/resources/config/config.properties";
    private static Properties properties = new Properties();

    static {
        try (FileInputStream inputStream = new FileInputStream(CONFIG_PATH)) {
            properties.load(inputStream);
        } catch (IOException e) {
            System.err.println("Failed to load configuration file: " + CONFIG_PATH);
            e.printStackTrace();
        }
    }

    public static String getConfigProperty(String propertyName) {
        String value = properties.getProperty(propertyName);
        if (value == null) {
            throw new IllegalArgumentException("Property '" + propertyName + "' not found in configuration file.");
        }
        return value;
    }

    public static Long getConfigPropertyLong(String propertyName) {
        Long value = Long.parseLong(properties.getProperty(propertyName));
        if (value == null) {
            throw new IllegalArgumentException("Property '" + propertyName + "' not found in configuration file.");
        }
        return value;
    }

}
