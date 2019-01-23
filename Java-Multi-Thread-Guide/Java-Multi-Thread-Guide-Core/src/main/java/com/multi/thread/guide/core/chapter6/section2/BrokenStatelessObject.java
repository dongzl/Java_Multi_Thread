package com.multi.thread.guide.core.chapter6.section2;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/23 上午9:51
 */
public class BrokenStatelessObject {

    public String doSomething(String s) {
        UnsafeSingleton us = UnsafeSingleton.INSTANCE;
        int i = us.doSomething(s);
        UnsafeStatefullObject ofs = new UnsafeStatefullObject();
        String str = ofs.doSomething(s, i);
        return str;
    }


    static class UnsafeStatefullObject {
        static Map<String, String> cache = new HashMap<>();

        public String doSomething(String s, int len) {
            String result = cache.get(s);
            if (null == result) {
                result = md5sum(result, len);
                cache.put(s, result);
            }
            return result;
        }

        private String md5sum(String result, int len) {
            return result;
        }
    }

    enum UnsafeSingleton {

        INSTANCE;

        public int state1;

        public int doSomething(String s) {
            return 0;
        }
    }
}
