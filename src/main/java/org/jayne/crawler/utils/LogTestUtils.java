package org.jayne.crawler.utils;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import sun.reflect.Reflection;

/**
 * 可以打印出类信息
 * Created by jayne on 2018/5/11.
 */
public class LogTestUtils {
    private static Logger logger = null;

    private LogTestUtils() {
    }

    public static Logger getLogger(){
        if(null == logger){
            logger = Logger.getLogger(Reflection.getCallerClass().getName());
        }
        return logger;
    }

    public static void debug(String msg) {
        log(Level.DEBUG, msg);
    }

    public static void debug(String msg, Throwable e) {
        log(Level.DEBUG, msg, e);
    }

    public static void info(String msg) {
        log(Level.INFO, msg);
    }

    public static void info(String msg, Throwable e) {
        log(Level.INFO, msg, e);
    }

    public static void warning(String msg) {
        log(Level.WARN, msg);
    }

    public static void warning(String msg, Throwable e) {
        log(Level.WARN, msg, e);
    }

    public static void error(String msg) {
        log(Level.ERROR, msg);
    }

    public static void error(String msg, Throwable e) {
        log(Level.ERROR, msg, e);
    }

    private static void log(Level level, String msg, Throwable e) {
        logger.log(level, msg, e);
    }

    private static void log(Level level, String msg) {
        logger.log(level, msg);
    }
}
