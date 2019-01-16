package com.multi.thread.guide.core.chapter3.section11;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/15 下午6:08
 */
public class ClassLazyInitDemo {

    public static void main(String[] args) throws Exception {
        System.out.println(Collaborator.class.hashCode());
        System.out.println(Collaborator.number);
        System.out.println(Collaborator.flag);
    }

    static class Collaborator {
        static int number = 1;
        static boolean flag = true;
        static {
            System.out.println("Collaborator initializing...");
        }
    }
}
