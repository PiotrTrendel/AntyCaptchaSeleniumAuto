package main.java.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

    public static String getConfigProperty(String propertyName) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/test/resources/config/global.properties"));
            return properties.getProperty(propertyName);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Long getConfigPropertyLong(String propertyName) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/test/resources/config/global.properties"));
            return Long.parseLong(properties.getProperty(propertyName));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
