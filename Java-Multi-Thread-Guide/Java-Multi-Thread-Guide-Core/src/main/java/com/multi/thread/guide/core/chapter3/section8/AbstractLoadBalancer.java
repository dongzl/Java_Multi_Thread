package com.multi.thread.guide.core.chapter3.section8;

import java.util.Random;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/15 上午9:46
 */
public abstract class AbstractLoadBalancer implements LoadBalancer {

    protected volatile Candidate candidate;

    protected final Random random;

    private Thread heartBeatThread;

    public AbstractLoadBalancer(Candidate candidate) {
        if (null == candidate)
        this.candidate = candidate;
        random = new Random();
    }
}
