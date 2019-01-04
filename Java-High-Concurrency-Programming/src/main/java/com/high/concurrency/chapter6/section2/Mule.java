package com.high.concurrency.chapter6.section2;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/04 下午6:46
 */
public class Mule implements IHouse, IAnimal, IDonkey {

    @Override
    public void eat() {
        System.out.println("Mule eat");
    }

    @Override
    public void run() {
        IHouse.super.run();
    }

    public static void main(String[] args) throws Exception {
        Mule m = new Mule();
        m.run();
        m.breath();
    }
}
