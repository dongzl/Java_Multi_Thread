package com.multi.thread.guide.core.chapter3.section8;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/15 上午9:35
 */
public class ServiceInvoker {

    private static final ServiceInvoker INSTANCE = new ServiceInvoker();

    private volatile LoadBalancer loadBalancer;

    private ServiceInvoker() {

    }

    public static ServiceInvoker getInstance() {
        return INSTANCE;
    }


}
