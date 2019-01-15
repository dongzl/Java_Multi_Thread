package com.multi.thread.guide.core.chapter3.section8;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/15 上午9:39
 */
public class Endpoint {

    public final String host;

    public final int port;

    public final int weight;

    private volatile boolean online = true;

    public Endpoint(String host, int port, int weight) {
        this.host = host;
        this.port = port;
        this.weight = weight;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public int getWeight() {
        return weight;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}
