package com.high.concurrency.chapter2;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2018/12/25 下午6:30
 */
public class StopThreadUnsafe2 {

    public static User u = new User();

    public static class User {
        private int id;

        private String name;

        public User() {
            id = 0;
            name = "0";
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static class ChangeObjectThread extends Thread {

        volatile boolean stopMe = false;

        public void setStopMe(boolean stopMe) {
            stopMe = true;
        }

        @Override
        public void run() {
            while (true) {
                if (stopMe) {
                    System.out.println("exit by stop me");
                    break;
                }
                synchronized (u) {
                    int v = (int)(System.currentTimeMillis() / 1000);
                    u.setId(v);
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    u.setName(String.valueOf(v));
                }
            }
        }
    }

    public static class ReadObjectThread extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (u) {
                    if (u.getId() != Integer.parseInt(u.getName())) {
                        System.out.println(u.toString());
                    }
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new ReadObjectThread().start();
        while (true) {
            Thread t = new ChangeObjectThread();
            t.start();
            Thread.sleep(150);
            t.stop();
        }
    }
}
