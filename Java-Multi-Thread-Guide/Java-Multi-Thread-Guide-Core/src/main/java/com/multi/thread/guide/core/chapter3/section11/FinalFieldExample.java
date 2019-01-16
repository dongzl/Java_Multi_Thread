package com.multi.thread.guide.core.chapter3.section11;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/16 上午9:42
 */
public class FinalFieldExample {

    final int x;
    int y;

    static FinalFieldExample instance;

    public FinalFieldExample() {
        x = 1;
        y = 2;
    }

    public static void writer() {
        instance = new FinalFieldExample();
    }

    public static void reader() {
        final FinalFieldExample theInstance = instance;
        if (theInstance != null) {
            int diff = theInstance.y - theInstance.x;
            print(diff);
        }
    }

    private static void print(int x) {

    }
}
