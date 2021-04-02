package task5.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

public class ConfigFileReader {

    private static Properties properties;
    private static String propertyFileName = "configuration.properties";

    public static void initConfigFileReader(){
        String propertyFilePath = ConfigFileReader.class.getClassLoader().getResource(propertyFileName).getFile();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(propertyFilePath.replaceAll("%20"," ")))) {
            properties = new Properties();
            properties.load(reader);
        } catch (Exception e) {
            Logger.error("Config file has not been initialized");
        }
    }

    public static String getApplicationUrl() {
        String url = properties.getProperty("url");
        if (url != null) return url;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }

    public static String getPathImage() {
        String nameImage = properties.getProperty("nameImage");
        String pathImage = ConfigFileReader.class.getClassLoader().getResource(nameImage).getFile();
        if (pathImage != null) {
            return pathImage.replaceAll("/", "\\\\");
        } else {
            throw new RuntimeException("url not specified in the Configuration.properties file.");
        }
    }
}
