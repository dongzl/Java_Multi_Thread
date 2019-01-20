package com.multi.thread.guide.core.chapter5.section3;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/19 下午10:05
 */
public class ServiceStart {

    public static void main(String[] args) throws Exception {
        ServiceManager.startServices();
        boolean allIsOk;
        allIsOk = ServiceManager.checkServiceStatus();
        if (allIsOk) {
            System.out.println("All services were sucessfully started!");
        } else {
            System.out.println("Some service(s) fialed to start, exiting JVM...");
            System.exit(1);
        }
    }
}
