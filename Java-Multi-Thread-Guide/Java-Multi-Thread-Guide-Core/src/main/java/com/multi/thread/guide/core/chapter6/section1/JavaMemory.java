package com.multi.thread.guide.core.chapter6.section1;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/23 上午9:32
 */
public class JavaMemory {

    public static void main(String[] args) throws Exception {
        String msg = args.length > 0 ? args[0] : null;
        ObjectX objx = new ObjectX();
        objx.greet(msg);
    }

    static class ObjectX implements Serializable {
        private static final long serialVersionUID = 1234567890L;

        private static AtomicInteger ID_Generator = new AtomicInteger(0);

        private Date timeCreated = new Date();

        private int id;

        public ObjectX() {
            this.id = ID_Generator.getAndIncrement();
        }

        public void greet(String message) {
            String msg = toString() + ":" + message;
            System.out.println(msg);
        }

        @Override
        public String toString() {
            return "ObjectX{" +
                    "timeCreated=" + timeCreated +
                    ", id=" + id +
                    '}';
        }
    }
}
