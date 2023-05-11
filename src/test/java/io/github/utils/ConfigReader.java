package io.github.utils;

import java.util.Properties;

public class ConfigReader {
    private Properties properties = null;
    private static ConfigReader configReader;

    private ConfigReader() {
        properties = PropertyUtils.loadProperty("src/test/resources/config.properties");
    }

    public static ConfigReader getInstance() {
        if (configReader == null) {
            configReader = new ConfigReader();
        }
        return configReader;
    }

    public String getPropertyValue(String key) {
        String prop = properties.getProperty(key);
        if (prop != null) return prop;
        else throw new RuntimeException(key + " not specified inside the config.properties");
    }

    public String getURL() {
        String prop = properties.getProperty("url");
        if (prop != null) return prop;
        else throw new RuntimeException("url not specified inside the config.properties");
    }

    public String getRemoteURL() {
        String prop = properties.getProperty("remoteURL");
        if (prop != null) return prop;
        else throw new RuntimeException("remoteURL not specified inside the config.properties");
    }

    public String getBrowserName() {
        String prop = properties.getProperty("browserName");
        if (prop != null) return prop;
        else throw new RuntimeException("browserName not specified inside the config.properties");
    }
}
