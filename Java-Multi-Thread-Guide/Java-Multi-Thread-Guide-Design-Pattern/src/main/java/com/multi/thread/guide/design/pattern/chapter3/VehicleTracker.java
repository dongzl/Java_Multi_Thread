package com.multi.thread.guide.design.pattern.chapter3;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author dongzonglei
 * @description
 * @date 2019-02-15 10:31
 */
public class VehicleTracker {

    private Map<String, LocationV2> locMap = new ConcurrentHashMap<>();

    public void updateLocation(String vehicleId, LocationV2 newLocation) {
        locMap.put(vehicleId, newLocation);
    }
}
