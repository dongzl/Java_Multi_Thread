package com.high.concurrency.chapter6.section2;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/04 下午7:23
 */
public class User {

    private Integer age;

    private String name;

    public User(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
