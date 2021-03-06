package com.multi.thread.guide.core.chapter2.section4;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/12 下午6:47
 */
public class AtomicityExample {

    private HostInfo hostInfo;

    public void updateHostInfo(String ip, int port) {
        hostInfo.setIp(ip);
        hostInfo.setPort(port);
    }

    public void updateHostInfo2(String ip, int port) {
        HostInfo hostInfo = new HostInfo();
        hostInfo.setIp(ip);
        hostInfo.setPort(port);
    }

    public void connectToHost() {
        String ip = hostInfo.getIp();
        int port = hostInfo.getPort();
        connectToHost(ip, port);
    }

    private void connectToHost(String ip, int port) {

    }

    public static class HostInfo {
        private String ip;

        private int port;

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }
    }
}
