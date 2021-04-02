package task5.utils;

import org.apache.log4j.LogManager;

public class Logger {

    public static final org.apache.log4j.Logger LOGGER = LogManager.getLogger(Logger.class);

    public static void fatal(Object message){
        LOGGER.fatal(message);
    }

    public static void error(Object message){
        LOGGER.error(message);
    }

    public static void info(Object message){
        LOGGER.info(message);
    }

    public static void trace(Object message){
        LOGGER.trace(message);
    }
}
