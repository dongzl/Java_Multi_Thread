package com.multi.thread.guide.core.chapter5.section3;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/19 下午10:05
 */
public class ServiceManager {

    static volatile CountDownLatch latch;

    static Set<Service> services;

    public static void startServices() {
        services = getServices();
        for (Service service : services) {
            service.start();
        }
    }

    public static boolean checkServiceStatus() {
        boolean allIsOK = true;
        try {
            latch.await();
        } catch (InterruptedException e) {
            return false;
        }
        for (Service service : services) {
            if (!service.isStarted()) {
                allIsOK = false;
                break;
            }
        }
        return allIsOK;
    }

    static Set<Service> getServices() {
        latch = new CountDownLatch(3);
        Set<Service> services = new HashSet<>();
        services.add(new SimpleServiceA(latch));
        services.add(new SimpleServiceB(latch));
        services.add(new SimpleServiceC(latch));
        return services;
    }
}
