package com.high.concurrency.chapter6.section2;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/04 下午6:45
 */
public interface IAnimal {

    default void breath() {
        System.out.println("breath");
    }
}
