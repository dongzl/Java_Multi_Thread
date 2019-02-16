package com.multi.thread.guide.design.pattern.chapter3;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dongzonglei
 * @description
 * @date 2019-02-15 15:32
 */
public final class MMSCRouter {

    private static volatile MMSCRouter instance = new MMSCRouter();

    private final Map<String, MMSCInfo> routeMap;

    public MMSCRouter() {
        this.routeMap = MMSCRouter.retrieveRouteMapFromDB();
    }

    private static Map<String, MMSCInfo> retrieveRouteMapFromDB() {
        Map<String, MMSCInfo> map = new HashMap<>();
        return map;
    }

    public static MMSCRouter getInstance() {
        return instance;
    }

    public MMSCInfo getMMSC(String msisdnPrefix) {
        return routeMap.get(msisdnPrefix);
    }

    public static void setInstance(MMSCRouter newInstance) {
        instance = newInstance;
    }

    private static Map<String, MMSCInfo> deepCopy(Map<String, MMSCInfo> m) {
        Map<String, MMSCInfo> result = new HashMap<>();
        for (String key : m.keySet()) {
            result.put(key, new MMSCInfo(m.get(key)));
        }
        return result;
    }

    public Map<String, MMSCInfo> getRouteMap() {
        return Collections.unmodifiableMap(deepCopy(routeMap));
    }
}
