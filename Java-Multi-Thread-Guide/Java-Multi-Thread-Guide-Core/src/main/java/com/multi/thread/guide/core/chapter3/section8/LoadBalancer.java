package com.multi.thread.guide.core.chapter3.section8;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/15 上午9:36
 */
public interface LoadBalancer {

    void updateCondidate(final Candidate candidate);

    Endpoint nextEndpoint();
}
