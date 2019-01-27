package com.multi.thread.guide.core.chapter7.section2;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/27 下午4:32
 */
public class Configuration {

    private final String name;

    private volatile int version;

    private volatile Map<String, String> configItemMap;

    public Configuration(String name, int version) {
        this.name = name;
        this.version = version;
        configItemMap = new HashMap<>();
    }

    public void setProperty(String key, String value) {
        configItemMap.put(key, value);
    }

    public String getProperty(String key) {
        return configItemMap.get(key);
    }

    public void update(Map<String, String> properties) {
        configItemMap = properties;
    }

    public String getName() {
        return name;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
