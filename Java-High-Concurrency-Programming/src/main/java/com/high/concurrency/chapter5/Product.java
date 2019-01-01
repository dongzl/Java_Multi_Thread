package com.high.concurrency.chapter5;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/01 下午9:07
 */
public final class Product {

    private final String no;

    private final String name;

    private final double price;

    public Product(String no, String name, double price) {
        this.no = no;
        this.name = name;
        this.price = price;
    }

    public String getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
