package org.jayne.crawler.utils;

/**
 * Created by jayne on 2018/5/9.
 */
public enum Environment {
    develop("开发环境"),
    online("线上环境");

    private String desc;

    private Environment(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }
}
