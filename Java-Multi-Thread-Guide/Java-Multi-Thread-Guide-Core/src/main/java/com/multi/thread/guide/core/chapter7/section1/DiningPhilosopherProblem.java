package com.multi.thread.guide.core.chapter7.section1;

import java.lang.reflect.Constructor;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/25 下午2:22
 */
public class DiningPhilosopherProblem {

    public static void main(String[] args) throws Exception {
        int numOfPhilosophers = args.length > 0 ? Integer.valueOf(args[0]) : 2;
        Chopstick[] chopsticks = new Chopstick[numOfPhilosophers];
        for (int i = 0; i < numOfPhilosophers; i++) {
            chopsticks[i] = new Chopstick(i);
        }
        String philosopherImplClassName = System.getProperty("x.philo.imp");
        if (null == philosopherImplClassName) {
            philosopherImplClassName = "DeadlockingPhilosopher";
        }
        for (int i = 0; i < numOfPhilosophers; i++) {
            createPhilosopher(philosopherImplClassName, i, chopsticks);
        }
    }

    private static void createPhilosopher(String philosopherImplClassName, int id, Chopstick[] chopsticks) throws Exception {
        int numOfPhilosophers = chopsticks.length;
        Class<Philosopher> philosopherClass = (Class<Philosopher>) Class.forName(DiningPhilosopherProblem.class.getPackage().getName() + "." + philosopherImplClassName);
        Constructor<Philosopher> constructor = philosopherClass.getConstructor(int.class, Chopstick.class, Chopstick.class);
        Philosopher philosopher = constructor.newInstance(id, chopsticks[id], chopsticks[(id + 1) % numOfPhilosophers]);
        //philosopher.start();
    }
}
