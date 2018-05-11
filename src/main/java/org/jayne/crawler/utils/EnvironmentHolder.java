package org.jayne.crawler.utils;

/**
 * Created by jayne on 2018/5/9.
 */
public class EnvironmentHolder {
    private static Environment ENVIRONMENT;

    private static String baseUrl;

    public EnvironmentHolder() {
    }

    public static Environment getEnvironment() {
        return ENVIRONMENT;
    }

    public void setEnvironment(String environment) {
        ENVIRONMENT = Environment.valueOf(environment);
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        EnvironmentHolder.baseUrl = baseUrl;
    }

    static {
        ENVIRONMENT = Environment.develop;
    }
}
