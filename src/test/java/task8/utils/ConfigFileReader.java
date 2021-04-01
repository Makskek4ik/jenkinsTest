package task8.utils;

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
            Logger.error(e.getMessage());
        }
    }

    public static String getApplicationUrl() {
        String url = properties.getProperty("url");
        if (url != null) return url;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }

    public static String getLogin() {
        String login = properties.getProperty("login");
        if (login != null) return login;
        else throw new RuntimeException("login not specified in the Configuration.properties file.");
    }

    public static String getPassword() {
        String password = properties.getProperty("password");
        if (password != null) return password;
        else throw new RuntimeException("password not specified in the Configuration.properties file.");
    }

    public static String getToken() {
        String token = properties.getProperty("token");
        if (token != null) return token;
        else throw new RuntimeException("token not specified in the Configuration.properties file.");
    }

    public static String getVersionVkApi() {
        String versionVkApi = properties.getProperty("versionVkApi");
        if (versionVkApi != null) return versionVkApi;
        else throw new RuntimeException("token not specified in the Configuration.properties file.");
    }

    public static int getPhotoIdentityPercentage() {
        String photoIdentityPercentage = properties.getProperty("photoIdentityPercentage");
        if (photoIdentityPercentage != null) return Integer.parseInt(photoIdentityPercentage);
        else throw new RuntimeException("token not specified in the Configuration.properties file.");
    }

    public static int getTimeOutSec() {
        String timeOutSec = properties.getProperty("timeOutSec");
        if (timeOutSec != null) return Integer.parseInt(timeOutSec);
        else throw new RuntimeException("token not specified in the Configuration.properties file.");
    }

    public static String getVkURL() {
        String vkURL = properties.getProperty("vkURL");
        if (vkURL != null) return vkURL;
        else throw new RuntimeException("token not specified in the Configuration.properties file.");
    }
}
